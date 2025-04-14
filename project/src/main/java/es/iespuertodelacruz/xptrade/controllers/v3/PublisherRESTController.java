package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.output.PublisherOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPublisherOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/publishers")
@Tag(name="v3 - Publisher ", description = "For administrators")
public class PublisherRESTController {


    /**
     * Properties
     */
    private IGenericService<Publisher, Integer, String> service;


    /**
     * Setters of the role service
     * @param service of the role
     */
    @Autowired
    public void setService(IGenericService<Publisher, Integer, String> service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PublisherOutputDTO> filteredList = IPublisherOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

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
        Publisher aux = service.findById(id);
        if (aux != null){
            PublisherOutputDTO dto = IPublisherOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<PublisherOutputDTO> response =
                    new CustomApiResponse<>(302, "Publisher found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<PublisherOutputDTO> errorResponse = new CustomApiResponse<>(404, "Publisher NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Publisher aux = service.findByName(name);
        if (aux != null){
            PublisherOutputDTO dto = IPublisherOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<PublisherOutputDTO> response =
                    new CustomApiResponse<>(202, "Publisher found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<PublisherOutputDTO> errorResponse = new CustomApiResponse<>(404, "Publisher NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(PublisherOutputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Publisher dbItem = service.add(dto.name());
            PublisherOutputDTO result = IPublisherOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody PublisherOutputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Publisher dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Publisher updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            PublisherOutputDTO result = IPublisherOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
