package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IRoleService;
import es.iespuertodelacruz.xptrade.dto.input.RoleInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IRoleOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<RoleOutputDTO> filteredList = IRoleOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

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
            RoleOutputDTO dto =  new RoleOutputDTO(aux.getId(),aux.getName());

            CustomApiResponse<RoleOutputDTO> response =
                    new CustomApiResponse<>(302, "Role found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<RoleOutputDTO> errorResponse = new CustomApiResponse<>(204, "Role NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Role aux = service.findByName(name);
        if (aux != null){
            RoleOutputDTO dto = IRoleOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<RoleOutputDTO> response =
                    new CustomApiResponse<>(202, "Role found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<RoleOutputDTO> errorResponse = new CustomApiResponse<>(204, "Role NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody RoleInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Role dbItem = service.add(dto.name());
            RoleOutputDTO result = IRoleOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody RoleOutputDTO dto) {

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(
                            new CustomApiResponse<>(
                                    400, "Invalid item ", null));
        }

        Role dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(
                            new CustomApiResponse<>(
                                    204, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Role updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            RoleOutputDTO result = IRoleOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error while trying to update", null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Role dbItem = service.findById(id);

        if(dbItem == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new CustomApiResponse<>(204, "NOT FOUND", null));
        }

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
