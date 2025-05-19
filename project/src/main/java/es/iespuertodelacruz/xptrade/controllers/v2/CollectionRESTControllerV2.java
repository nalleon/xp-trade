package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.ICollectionService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.input.CollectionInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.input.ICollectionInputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.ICollectionOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/collections")
@Tag(name="v2 - Collection ", description = "For authenticated users")
public class CollectionRESTControllerV2 {

    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * Properties
     */
    private ICollectionService service;
    private IGameService gameService;
    private IUserService userService;

    /**
     * Setters of the collection service
     * @param service of the collection
     */
    @Autowired
    public void setService(ICollectionService service) {
        this.service = service;
    }
    /**
     * Setters of the collection service
     * @param gameService of the collection
     */
    @Autowired
    public void setGameService(IGameService gameService) {
        this.gameService = gameService;
    }
    /**
     * Setters of the collection service
     * @param userService of the collection
     */
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CollectionOutputDTO> filteredList = ICollectionOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

        if (filteredList.isEmpty()) {
            String message = "There are no collections";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getAllByUser(@PathVariable String username) {
        User filter = userService.findByUsername(username);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }

        List<CollectionOutputDTO> filteredList = ICollectionOutputDTOMapper.INSTANCE.toDTOList(service.findByUser(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no collections";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Collection aux = service.findById(id);
        if (aux != null){
            CollectionOutputDTO dto = ICollectionOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<CollectionOutputDTO> response =
                    new CustomApiResponse<>(200, "Collection OK", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<CollectionOutputDTO> errorResponse = new CustomApiResponse<>(204, "Collection NOT OK", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }




    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody CollectionInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "Item cannot be null", null));
        }

        try {
            Collection aux = ICollectionInputDTOMapper.INSTANCE.toDomain(dto);


            User userDb = userService.findByUsername(aux.getUser().getUsername());

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            Collection dbItem = service.add(userDb);

            CollectionOutputDTO result = ICollectionOutputDTOMapper.INSTANCE.toDTO(dbItem);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Item created successfully", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to add item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody CollectionInputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Collection dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "Item not found", null));
        }

        try {

            Collection aux = ICollectionInputDTOMapper.INSTANCE.toDomain(dto);

            User userDb = userService.findByUsername(aux.getUser().getUsername());

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }


            Collection updatedDbItem = service.update(id, userDb);

            if(updatedDbItem == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new CustomApiResponse<>(204, "Item does not exists", null));
            }


            CollectionOutputDTO result = ICollectionOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to update", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        boolean deleted = service.delete(id);

        if (deleted) {
            String message = "Item deleted correctly";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        } else {
            String message = "Unable to delete item with id: " + id;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, message, null));
        }
    }

}
