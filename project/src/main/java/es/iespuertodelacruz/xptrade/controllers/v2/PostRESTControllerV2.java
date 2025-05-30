package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IPostService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.input.ContentUpdateDTO;
import es.iespuertodelacruz.xptrade.dto.input.PostInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.input.IPostInputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPostOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.security.CustomUserDetails;
import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static es.iespuertodelacruz.xptrade.shared.security.JwtFilter.authHeader;
import static es.iespuertodelacruz.xptrade.shared.security.JwtFilter.authHeaderTokenPrefix;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/posts")
@Tag(name="v2 - Post ", description = "For authenticated users")
public class PostRESTControllerV2 {

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

    @GetMapping("/latest")
    public ResponseEntity<?> getAllLatest() {
        List<PostOutputDTO> filteredList = IPostOutputDTOMapper.INSTANCE.toDTOList(service.findAllLatest());

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
                    new CustomApiResponse<>(200, "Post found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<PostOutputDTO> errorResponse = new CustomApiResponse<>(204, "Post NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }

    @GetMapping("/users/{username}/latest")
    public ResponseEntity<?> getLatestPost(@PathVariable String username) {
        Post aux = service.findLatest(new User(username));

        if (aux != null){
            PostOutputDTO dto = IPostOutputDTOMapper.INSTANCE.toDTO(aux);
            CustomApiResponse<PostOutputDTO> response =
                    new CustomApiResponse<>(200, "Post found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<PostOutputDTO> errorResponse = new CustomApiResponse<>(204, "Post NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody PostInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Post aux = IPostInputDTOMapper.INSTANCE.toDomain(dto);

            Game gameDb = gameService.add(aux.getGame().getTitle(), aux.getGame().getCoverArt(), aux.getGame().getSlug(),
                    aux.getGame().getRating(), aux.getGame().getReleased(), aux.getGame().getTagSet(),
                    aux.getGame().getDeveloperSet(), aux.getGame().getGenreSet(), aux.getGame().getPlatformSet(),
                    aux.getGame().getPublisherSet());

            if(gameDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            User userDb = userService.findByUsername(aux.getUser().getUsername());

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            Post dbItem = service.add(gameDb, userDb, aux.getContent(), aux.getPicture());

            PostOutputDTO result = IPostOutputDTOMapper.INSTANCE.toDTO(dbItem);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Usuario creado correctamente", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error al intentar registrar el item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody ContentUpdateDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Post dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "User NOT found", null));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        User userDb = userService.findByUsername(username);

        if (userDb.getId() != dbItem.getUser().getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomApiResponse<>(401, "Unauthorized", null));
        }

        try {

            dbItem.setContent(dto.content());

            Post updatedDbItem = service.update(dbItem.getId(), dbItem.getGame(), dbItem.getUser(), dbItem.getContent(), dbItem.getPicture());

            if(updatedDbItem == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new CustomApiResponse<>(204, "Item update failed", null));
            }

            PostOutputDTO result = IPostOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to update", null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Post dbItem = service.findById(id);

        if(dbItem == null){
            ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "Not found", null));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        User userDb = userService.findByUsername(username);


        if (dbItem != null && userDb.getId() != dbItem.getUser().getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomApiResponse<>(401, "Unauthorized", null));
        }

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