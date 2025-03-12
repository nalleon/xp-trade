package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.DeveloperDTO;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
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
@RequestMapping("/api/v3/developers")
@Tag(name="v3 - Developer ", description = "For administrators")
public class DeveloperRESTController {


    /**
     * Properties
     */
    private IGenericService<Developer, Integer, String> service;


    /**
     * Setters of the role service
     * @param service of the role
     */
    @Autowired
    public void setService(IGenericService<Developer, Integer, String> service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<DeveloperDTO> filteredList = service.findAll().stream().map(item ->
                new DeveloperDTO(item.getId(),item.getName())).collect(Collectors.toList());

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
        Developer aux = service.findById(id);
        if (aux != null){
            DeveloperDTO dto =  new DeveloperDTO(aux.getId(), aux.getName());

            CustomApiResponse<DeveloperDTO> response =
                    new CustomApiResponse<>(302, "Developer found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<DeveloperDTO> errorResponse = new CustomApiResponse<>(404, "Developer NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Developer aux = service.findByName(name);
        if (aux != null){
            DeveloperDTO dto =  new DeveloperDTO(aux.getId(), aux.getName());

            CustomApiResponse<DeveloperDTO> response =
                    new CustomApiResponse<>(202, "Developer found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<DeveloperDTO> errorResponse = new CustomApiResponse<>(404, "Developer NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> create(DeveloperDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Developer dbItem = service.add(dto.name());
            DeveloperDTO result = new DeveloperDTO(dbItem.getId(), dbItem.getName());
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
            @RequestBody DeveloperDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Developer dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Developer updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            DeveloperDTO result = new DeveloperDTO(updatedDbItem.getId(), updatedDbItem.getName());

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error while trying to update", null));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Developer dbItem = service.findById(id);

        if(dbItem.getName().equals(Globals.ADMIN)){
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
