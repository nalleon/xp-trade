package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IFavoriteService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.input.FavoriteInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.FavoriteOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.FavoriteOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.input.IFavoriteInputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.input.IFavoriteInputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IFavoriteOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IFavoriteOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/favorites")
@Tag(name="v2 - Favorite ", description = "For authenticated users")
public class FavoriteRESTControllerV2 {

    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * Properties
     */
    private IFavoriteService service;
    private IGameService gameService;
    private IUserService userService;

    /**
     * Setters of the favorite service
     * @param service of the favorite
     */
    @Autowired
    public void setService(IFavoriteService service) {
        this.service = service;
    }
    /**
     * Setters of the favorite service
     * @param gameService of the favorite
     */
    @Autowired
    public void setGameService(IGameService gameService) {
        this.gameService = gameService;
    }
    /**
     * Setters of the favorite service
     * @param userService of the favorite
     */
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<FavoriteOutputDTO> filteredList = IFavoriteOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

        if (filteredList.isEmpty()) {
            String message = "There are no favorites";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/games/{title}")
    public ResponseEntity<?> getAllByGame(@PathVariable String title) {
        Game filter = gameService.findByTitle(title);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }

        List<FavoriteOutputDTO> filteredList = IFavoriteOutputDTOMapper.INSTANCE.toDTOList(service.findByGame(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no favorites";
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

        List<FavoriteOutputDTO> filteredList = IFavoriteOutputDTOMapper.INSTANCE.toDTOList(service.findByUser(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no favorites";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Favorite aux = service.findById(id);
        if (aux != null){
            FavoriteOutputDTO dto = IFavoriteOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<FavoriteOutputDTO> response =
                    new CustomApiResponse<>(302, "Favorite found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<FavoriteOutputDTO> errorResponse = new CustomApiResponse<>(404, "Favorite NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }




    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody FavoriteInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "Item cannot be null", null));
        }

        System.out.println("Incoming DTO: " + dto);

        try {
            Favorite aux = IFavoriteInputDTOMapper.INSTANCE.toDomain(dto);

            Game gameDb = gameService.add(aux.getGame().getTitle(), aux.getGame().getCoverArt(),
                    aux.getGame().getDeveloperSet(), aux.getGame().getGenreSet(), aux.getGame().getPlatformSet(),
                    aux.getGame().getPublisherSet(), aux.getGame().getRegionSet());

            if(gameDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            User userDb = userService.findByUsername(aux.getUser().getUsername());

            System.out.println("USER: " + userDb);

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }



            Favorite dbItem = service.add(gameDb, userDb);

            System.out.println("fav " + dbItem);


            FavoriteOutputDTO result = IFavoriteOutputDTOMapper.INSTANCE.toDTO(dbItem);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Item created successfully", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error while trying to add item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody FavoriteInputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Favorite dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "User NOT found", null));
        }

        try {

            Favorite aux = IFavoriteInputDTOMapper.INSTANCE.toDomain(dto);

            Game gameDb = gameService.add(aux.getGame().getTitle(), aux.getGame().getCoverArt(),
                    aux.getGame().getDeveloperSet(), aux.getGame().getGenreSet(), aux.getGame().getPlatformSet(),
                    aux.getGame().getPublisherSet(), aux.getGame().getRegionSet());

            if(gameDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            User userDb = userService.findByUsername(aux.getUser().getUsername());

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }


            Favorite updatedDbItem = service.update(id, gameDb, userDb);

            if(updatedDbItem == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomApiResponse<>(404, "Item does not exists", null));
            }


            FavoriteOutputDTO result = IFavoriteOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error while trying to update", null));
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, message, null));
        }
    }

}
