package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@RestController
@CrossOrigin
@RequestMapping("/api/v2/games")
@Tag(name="v2 - Game", description = "For authenticated users")
public class GameRESTControllerV2 {


    /**
     * Properties
     */
    private IGameService service;
    private IGenericService<Platform, Integer, String> platformService;
    private IGenericService<Genre, Integer, String> genreService;
    private IGenericService<Developer, Integer, String> developerService;
    private IGenericService<Publisher, Integer, String> publisherService;
    private IGenericService<Region, Integer, String> regionService;


    /**
     * Setters of the game service
     * @param service of the game
     */
    @Autowired
    public void setService(IGameService service) {
        this.service = service;
    }

    /**
     * Setters of the game service
     * @param platformService of the game
     */
    @Autowired
    public void setPlatformService(IGenericService<Platform, Integer, String> platformService) {
        this.platformService = platformService;
    }
    /**
     * Setters of the game service
     * @param genreService of the game
     */
    @Autowired
    public void setGenreService(IGenericService<Genre, Integer, String> genreService) {
        this.genreService = genreService;
    }
    /**
     * Setters of the game service
     * @param developerService of the game
     */
    @Autowired
    public void setDeveloperService(IGenericService<Developer, Integer, String> developerService) {
        this.developerService = developerService;
    }
    /**
     * Setters of the game service
     * @param publisherService of the game
     */
    @Autowired
    public void setPublisherService(IGenericService<Publisher, Integer, String> publisherService) {
        this.publisherService = publisherService;
    }
    /**
     * Setters of the game service
     * @param regionService of the game
     */
    @Autowired
    public void setRegionService(IGenericService<Region, Integer, String> regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GameOutputDTO> filteredList = IGameOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

        if (filteredList.isEmpty()) {
            String message = "There are no items";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/platforms/{name}")
    public ResponseEntity<?> getAllByPlatform(@PathVariable String name) {

        Platform filter = platformService.findByName(name);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }


        List<GameOutputDTO> filteredList = IGameOutputDTOMapper.INSTANCE.toDTOList(service.findAllByPlatform(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no items";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/regions/{name}")
    public ResponseEntity<?> getAllByRegion(@PathVariable String name) {
        Region filter = regionService.findByName(name);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }


        List<GameOutputDTO> filteredList = IGameOutputDTOMapper.INSTANCE.toDTOList(service.findAllByRegion(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no items";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/developers/{name}")
    public ResponseEntity<?> getAllByDeveloper(@PathVariable String name) {

        Developer filter = developerService.findByName(name);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }


        List<GameOutputDTO> filteredList = IGameOutputDTOMapper.INSTANCE.toDTOList(service.findAllByDeveloper(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no items";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }


    @GetMapping("/publishers/{name}")
    public ResponseEntity<?> getAllByPublisher(@PathVariable String name) {

        Publisher filter = publisherService.findByName(name);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }


        List<GameOutputDTO> filteredList = IGameOutputDTOMapper.INSTANCE.toDTOList(service.findAllByPublisher(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no items";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }


    @GetMapping("/genres/{name}")
    public ResponseEntity<?> getAllByGenre(@PathVariable String name) {

        Genre filter = genreService.findByName(name);

        if(filter == null){
            String message = "Filter do NOT exists";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }


        List<GameOutputDTO> filteredList = IGameOutputDTOMapper.INSTANCE.toDTOList(service.findAllByGenre(filter));

        if (filteredList.isEmpty()) {
            String message = "There are no items";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Game aux = service.findById(id);
        if (aux != null){
            GameOutputDTO dto = IGameOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<GameOutputDTO> response = new CustomApiResponse<>(200, "Game found", dto);
            return ResponseEntity.ok(response);
        }

        CustomApiResponse<GameOutputDTO> errorResponse = new CustomApiResponse<>(404, "Game NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @GetMapping("/titles/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title) {
        Game aux = service.findByTitle(title);
        if (aux != null){
            GameOutputDTO dto = IGameOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<GameOutputDTO> response = new CustomApiResponse<>(200, "Game found", dto);
            return ResponseEntity.ok(response);
        }

        CustomApiResponse<GameOutputDTO> errorResponse = new CustomApiResponse<>(404, "Game NOT found", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}