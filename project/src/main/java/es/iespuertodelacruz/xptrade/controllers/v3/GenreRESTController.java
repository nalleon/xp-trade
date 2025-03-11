package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IRoleService;
import es.iespuertodelacruz.xptrade.dto.GenreDTO;
import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import es.iespuertodelacruz.xptrade.shared.utils.ApiResponse;
import es.iespuertodelacruz.xptrade.shared.utils.Globals;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/genres")
@Tag(name="v3 - Genre ", description = "For administrators")
public class GenreRESTController {

    /**
     * Properties
     */
    private IGenericService<Genre, Integer, String> service;


    /**
     * Setters of the role service
     * @param service of the role
     */
    @Autowired
    public void setService(IGenericService<Genre, Integer, String> service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GenreDTO> filteredList = service.findAll().stream().map(item ->
                new GenreDTO(item.getId(),item.getName())).collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            String message = "There are no roles";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new ApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Genre aux = service.findById(id);
        if (aux != null){
            GenreDTO dto =  new GenreDTO(aux.getId(), aux.getName());

            ApiResponse<GenreDTO> response =
                    new ApiResponse<>(302, "Genre found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        ApiResponse<GenreDTO> errorResponse = new ApiResponse<>(404, "Genre NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Genre aux = service.findByName(name);
        if (aux != null){
            GenreDTO dto =  new GenreDTO(aux.getId(), aux.getName());

            ApiResponse<GenreDTO> response =
                    new ApiResponse<>(202, "Genre found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        ApiResponse<GenreDTO> errorResponse = new ApiResponse<>(404, "Genre NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(GenreDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Genre dbItem = service.add(dto.name());
            GenreDTO result = new GenreDTO(dbItem.getId(), dbItem.getName());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Usuario creado correctamente", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error al intentar registrar el item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody GenreDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Genre dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Genre updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            GenreDTO result = new GenreDTO(updatedDbItem.getId(), updatedDbItem.getName());

            return ResponseEntity.ok(new ApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error while trying to update", null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Genre dbItem = service.findById(id);

        if(dbItem.getName().equals(Globals.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new ApiResponse<>(403, "Forbidden action", null));
        }

        boolean deleted = service.delete(id);

        if (deleted) {
            String message = "Item deleted correctly";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponse<>(204, message, null));
        } else {
            String message = "Unable to delete item with id: " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, message, null));
        }
    }

}
