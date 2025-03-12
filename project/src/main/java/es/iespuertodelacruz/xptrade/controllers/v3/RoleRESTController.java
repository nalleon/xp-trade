package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IRoleService;
import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
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
@Tag(name="v3 - Role ", description = "For administrators")
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
        List<RoleDTO> filteredList = service.findAll().stream().map(item ->
                new RoleDTO(item.getId(), item.getName())).collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            String message = "There are no roles";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Role aux = service.findById(id);
        if (aux != null){
            RoleDTO dto =  new RoleDTO(aux.getId(),aux.getName());

            CustomApiResponse<RoleDTO> response =
                    new CustomApiResponse<>(302, "Role found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<RoleDTO> errorResponse = new CustomApiResponse<>(404, "Role NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Role aux = service.findByName(name);
        if (aux != null){
            RoleDTO dto =  new RoleDTO(aux.getId(),aux.getName());

            CustomApiResponse<RoleDTO> response =
                    new CustomApiResponse<>(202, "Role found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<RoleDTO> errorResponse = new CustomApiResponse<>(404, "Role NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> create(RoleDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Role dbItem = service.add(dto.name());
            RoleDTO result = new RoleDTO(dbItem.getId(),dbItem.getName());
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
            @RequestBody RoleDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Role dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "User NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Role updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            RoleDTO result = new RoleDTO(updatedDbItem.getId(),updatedDbItem.getName());

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error while trying to update", null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Role dbItem = service.findById(id);

        if(dbItem.getName().equals(ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new CustomApiResponse<>(403, "Forbidden action", null));
        }

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
