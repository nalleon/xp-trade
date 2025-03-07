package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IRoleService;
import es.iespuertodelacruz.xptrade.dto.role.RoleDTO;
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
}
