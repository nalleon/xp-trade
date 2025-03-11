package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IRoleService;
import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserUpdateInputDTO;
import es.iespuertodelacruz.xptrade.shared.utils.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/roles")
@Tag(name="v3", description = "For administrators")
public class RoleRESTController {

    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * Properties
     */
    private IRoleService service;


    /**
     * Setters of the role service
     * @param service of the role
     */
    @Autowired
    public void setService(IRoleService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<RoleDTO> filteredList = service.findAll().stream().map(usuario ->
                new RoleDTO(usuario.getName())).collect(Collectors.toList());

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
        Role aux = service.findById(id);
        if (aux != null){
            RoleDTO dto =  new RoleDTO(aux.getName());

            ApiResponse<RoleDTO> response =
                    new ApiResponse<>(302, "Role found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        ApiResponse<RoleDTO> errorResponse = new ApiResponse<>(404, "Role NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Role aux = service.findByName(name);
        if (aux != null){
            RoleDTO dto =  new RoleDTO(aux.getName());

            ApiResponse<RoleDTO> response =
                    new ApiResponse<>(202, "Role found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        ApiResponse<RoleDTO> errorResponse = new ApiResponse<>(404, "Role NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(RoleDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(400, "El usuario no puede ser nulo", null));
        }

        try {
            Role dbItem = service.add(dto.name());
            RoleDTO result = new RoleDTO(dbItem.getName());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Usuario creado correctamente", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error al intentar registrar el usuario", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody RoleDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Role dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "User NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Role updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            RoleDTO result = new RoleDTO(updatedDbItem.getName());

            return ResponseEntity.ok(new ApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error while trying to update", null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Role dbItem = service.findById(id);

        if(dbItem.getName().equals(ADMIN)){
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
