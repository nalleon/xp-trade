package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IPostService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPostOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/posts")
@Tag(name="v3 - Post ", description = "For administrators")
public class PostRESTController {

    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * Properties
     */
    private IPostService service;
    private IGameService gameService;
    private IUserService userService;

    /**
     * Setters of the post service
     * @param service of the post
     */
    @Autowired
    public void setService(IPostService service) {
        this.service = service;
    }
    /**
     * Setters of the post service
     * @param gameService of the post
     */
    @Autowired
    public void setGameService(IGameService gameService) {
        this.gameService = gameService;
    }
    /**
     * Setters of the post service
     * @param userService of the post
     */
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PostOutputDTO> filteredList = IPostOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

        if (filteredList.isEmpty()) {
            String message = "There are no posts";
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

        List<PostOutputDTO> filteredList = IPostOutputDTOMapper.INSTANCE.toDTOList(service.findByGame(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no posts";
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

        List<PostOutputDTO> filteredList = IPostOutputDTOMapper.INSTANCE.toDTOList(service.findByUser(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no posts";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Post aux = service.findById(id);
        if (aux != null){
            PostOutputDTO dto = IPostOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<PostOutputDTO> response =
                    new CustomApiResponse<>(302, "Post found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<PostOutputDTO> errorResponse = new CustomApiResponse<>(404, "Post NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }




    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(PostOutputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Post aux = IPostOutputDTOMapper.INSTANCE.toDomain(dto);
            Post dbItem = service.add(aux.getGame(), aux.getUser(), aux.getContent(), aux.getPicture());
            PostOutputDTO result = IPostOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody PostOutputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Post dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "User NOT found", null));
        }

        try {

            Post aux = IPostOutputDTOMapper.INSTANCE.toDomain(dto);

            Post updatedDbItem = service.update(aux.getId(), aux.getGame(), aux.getUser(), aux.getContent(), aux.getPicture());

            PostOutputDTO result = IPostOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
