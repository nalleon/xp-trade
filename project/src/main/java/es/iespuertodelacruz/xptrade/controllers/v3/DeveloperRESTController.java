package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.input.DeveloperInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.DeveloperOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IDeveloperOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<DeveloperOutputDTO> filteredList = IDeveloperOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

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
            DeveloperOutputDTO dto = IDeveloperOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<DeveloperOutputDTO> response =
                    new CustomApiResponse<>(302, "Developer found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<DeveloperOutputDTO> errorResponse = new CustomApiResponse<>(204, "Developer NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Developer aux = service.findByName(name);
        if (aux != null){
            DeveloperOutputDTO dto = IDeveloperOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<DeveloperOutputDTO> response =
                    new CustomApiResponse<>(200, "Developer found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<DeveloperOutputDTO> errorResponse = new CustomApiResponse<>(204, "Developer NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody DeveloperInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Developer dbItem = service.add(dto.name());
            DeveloperOutputDTO result = IDeveloperOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody DeveloperInputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Developer dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Developer updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            DeveloperOutputDTO result = IDeveloperOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to update", null));
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
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, message, null));
        }
    }

}
