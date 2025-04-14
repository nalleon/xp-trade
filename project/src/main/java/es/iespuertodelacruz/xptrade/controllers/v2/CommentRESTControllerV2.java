package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.ICommentService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IPostService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.output.CommentOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.ICommentOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/comments")
@Tag(name="v2 - Comment ", description = "For authenticated users")
public class CommentRESTControllerV2 {

    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * Properties
     */
    private ICommentService service;
    private IPostService postService;
    private IUserService userService;

    /**
     * Setters of the comment service
     * @param service of the comment
     */
    @Autowired
    public void setService(ICommentService service) {
        this.service = service;
    }
    /**
     * Setters of the comment service
     * @param gameService of the comment
     */
    @Autowired
    public void setPostService(IPostService gameService) {
        this.postService = gameService;
    }
    /**
     * Setters of the comment service
     * @param userService of the comment
     */
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CommentOutputDTO> filteredList = ICommentOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

        if (filteredList.isEmpty()) {
            String message = "There are no comments";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getAllByPost(@PathVariable int id) {
        Post filter = postService.findById(id);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }

        List<CommentOutputDTO> filteredList = ICommentOutputDTOMapper.INSTANCE.toDTOList(service.findByPost(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no comments";
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

        List<CommentOutputDTO> filteredList = ICommentOutputDTOMapper.INSTANCE.toDTOList(service.findByUser(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no comments";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Comment aux = service.findById(id);
        if (aux != null){
            CommentOutputDTO dto = ICommentOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<CommentOutputDTO> response =
                    new CustomApiResponse<>(302, "Comment found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<CommentOutputDTO> errorResponse = new CustomApiResponse<>(404, "Comment NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(CommentOutputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Comment aux = ICommentOutputDTOMapper.INSTANCE.toDomain(dto);
            Comment dbItem = service.add(aux.getPost(), aux.getUser(), aux.getContent());
            CommentOutputDTO result = ICommentOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody CommentOutputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Comment dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "User NOT found", null));
        }

        try {

            Comment aux = ICommentOutputDTOMapper.INSTANCE.toDomain(dto);

            Comment updatedDbItem = service.update(aux.getId(), aux.getPost(), aux.getUser(), aux.getContent());

            CommentOutputDTO result = ICommentOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
