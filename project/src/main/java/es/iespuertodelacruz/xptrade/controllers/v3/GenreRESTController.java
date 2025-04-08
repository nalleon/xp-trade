package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.output.GenreOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGenreOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<GenreOutputDTO> filteredList = IGenreOutputDTOMapper.INSTANCE.toDTOList(service.findAll());
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
        Genre aux = service.findById(id);
        if (aux != null){
            GenreOutputDTO dto = IGenreOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<GenreOutputDTO> response =
                    new CustomApiResponse<>(302, "Genre found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<GenreOutputDTO> errorResponse = new CustomApiResponse<>(404, "Genre NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        Genre aux = service.findByName(name);

        if (aux != null){
            GenreOutputDTO dto = IGenreOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<GenreOutputDTO> response =
                    new CustomApiResponse<>(202, "Genre found", dto);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        }

        CustomApiResponse<GenreOutputDTO> errorResponse = new CustomApiResponse<>(404, "Genre NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(GenreOutputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El item no puede ser nulo", null));
        }

        try {
            Genre dbItem = service.add(dto.name());
            GenreOutputDTO result = IGenreOutputDTOMapper.INSTANCE.toDTO(dbItem);
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
            @RequestBody GenreOutputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Genre dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "Item NOT found", null));
        }

        try {

            dbItem.setName(dto.name());

            Genre updatedDbItem = service.update(dbItem.getId(), dbItem.getName());

            GenreOutputDTO result = IGenreOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
