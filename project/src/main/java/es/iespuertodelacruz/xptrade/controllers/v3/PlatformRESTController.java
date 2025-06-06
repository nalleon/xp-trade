package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.input.PlatformInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PlatformOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPlatformOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/platforms")
@Tag(name="v3 - Platform ", description = "For administrators")
public class PlatformRESTController {

    /**
     * Properties
     */
    private IGenericService<Platform, Integer, String> service;


    /**
     * Setters of the role service
     * @param service of the role
     */
    @Autowired
    public void setService(IGenericService<Platform, Integer, String> service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PlatformOutputDTO> filteredList = IPlatformOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

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
        Platform aux = service.findById(id);
        if (aux != null){
            PlatformOutputDTO dto =  IPlatformOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<PlatformOutputDTO> response =
                    new CustomApiResponse<>(200, "Platform found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<PlatformOutputDTO> errorResponse = new CustomApiResponse<>(204, "Platform NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Platform aux = service.findByName(name);
        if (aux != null){
            PlatformOutputDTO dto = IPlatformOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<PlatformOutputDTO> response =
                    new CustomApiResponse<>(200, "Platform found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<PlatformOutputDTO> errorResponse = new CustomApiResponse<>(204, "Platform NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody PlatformInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Platform dbItem = service.add(dto.name());
            PlatformOutputDTO result = IPlatformOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody PlatformInputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Platform dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Platform updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            PlatformOutputDTO result = IPlatformOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
