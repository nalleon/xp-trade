package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.input.TagInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.TagOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.ITagOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/tags")
@io.swagger.v3.oas.annotations.tags.Tag(name="v3 - Tag ", description = "For administrators")
public class TagRESTController {

    /**
     * Properties
     */
    private IGenericService<Tag, Integer, String> service;


    /**
     * Setters of the role service
     * @param service of the role
     */
    @Autowired
    public void setService(IGenericService<Tag, Integer, String> service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<TagOutputDTO> filteredList = ITagOutputDTOMapper.INSTANCE.toDTOList(service.findAll());
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
        Tag aux = service.findById(id);
        if (aux != null){
            TagOutputDTO dto = ITagOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<TagOutputDTO> response =
                    new CustomApiResponse<>(200, "Tag found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<TagOutputDTO> errorResponse = new CustomApiResponse<>(204, "Tag NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Tag aux = service.findByName(name);

        if (aux != null){
            TagOutputDTO dto = ITagOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<TagOutputDTO> response =
                    new CustomApiResponse<>(202, "Tag found", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<TagOutputDTO> errorResponse = new CustomApiResponse<>(204, "Tag NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody TagInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Tag dbItem = service.add(dto.name());
            TagOutputDTO result = ITagOutputDTOMapper.INSTANCE.toDTO(dbItem);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Usuario creado correctamente", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(500, "Error al intentar registrar el item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody TagInputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Tag dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Tag updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            TagOutputDTO result = ITagOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
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
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(500, message, null));
        }
    }

}
