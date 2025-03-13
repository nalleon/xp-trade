package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IFavoriteService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.FavoriteDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.IFavoriteDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/favorites")
@Tag(name="v3 - Favorite ", description = "For administrators")
public class FavoriteRESTController {

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
        List<FavoriteDTO> filteredList = IFavoriteDTOMapper.INSTANCE.toDTOList(service.findAll());

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

        List<FavoriteDTO> filteredList = IFavoriteDTOMapper.INSTANCE.toDTOList(service.findByGame(filter));

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

        List<FavoriteDTO> filteredList = IFavoriteDTOMapper.INSTANCE.toDTOList(service.findByUser(filter));

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
            FavoriteDTO dto = IFavoriteDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<FavoriteDTO> response =
                    new CustomApiResponse<>(302, "Favorite found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<FavoriteDTO> errorResponse = new CustomApiResponse<>(404, "Favorite NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }




    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> create(FavoriteDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Favorite aux = IFavoriteDTOMapper.INSTANCE.toDomain(dto);
            Favorite dbItem = service.add(aux.getGame(), aux.getUser());
            FavoriteDTO result = IFavoriteDTOMapper.INSTANCE.toDTO(dbItem);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Usuario creado correctamente", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error al intentar registrar el item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody FavoriteDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Favorite dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "User NOT found", null));
        }

        try {

            Favorite aux = IFavoriteDTOMapper.INSTANCE.toDomain(dto);

            Favorite updatedDbItem = service.update(aux.getId(), aux.getGame(), aux.getUser());

            FavoriteDTO result = IFavoriteDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
