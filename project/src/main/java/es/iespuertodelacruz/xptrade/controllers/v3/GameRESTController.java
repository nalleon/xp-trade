package es.iespuertodelacruz.xptrade.controllers.v3;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameOutputDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/games")
@Tag(name="v3 - Game", description = "For administrators")
public class GameRESTController {


    /**
     * Properties
     */
    private IGameService service;
    private IGenericService<Platform, Integer, String> platformService;
    private IGenericService<Genre, Integer, String> genreService;
    private IGenericService<Developer, Integer, String> developerService;
    private IGenericService<Publisher, Integer, String> publisherService;
    private IGenericService<Region, Integer, String> regionService;

    private FileStorageService storageService;


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
     * @param storageService of the game
     */
    @Autowired
    public void setStorageService(FileStorageService storageService) {
        this.storageService = storageService;
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


    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(GameOutputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "El usuario no puede ser nulo", null));
        }

        try {

            Game aux = IGameOutputDTOMapper.INSTANCE.toDomain(dto);
            
            Game dbItem = service.add(aux.getTitle(), aux.getCoverArt(), 
                    aux.getDeveloperSet(), aux.getGenreSet(), aux.getPlatformSet(),
                    aux.getPublisherSet(), aux.getRegionSet());

            GameOutputDTO result = IGameOutputDTOMapper.INSTANCE.toDTO(dbItem);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Usuario creado correctamente", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, "Error al intentar registrar el usuario", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody GameOutputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "Game cannot be null", null));
        }

        Game dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(404, "Game NOT found", null));
        }

        try {

            Game aux = IGameOutputDTOMapper.INSTANCE.toDomain(dto);
            
            dbItem.setTitle(aux.getTitle());
            dbItem.setCoverArt(aux.getCoverArt());
            dbItem.setPublisherSet(aux.getPublisherSet());
            dbItem.setDeveloperSet(aux.getDeveloperSet());
            dbItem.setRegionSet(aux.getRegionSet());
            dbItem.setPlatformSet(aux.getPlatformSet());
            dbItem.setGenreSet(aux.getGenreSet());


            Game updatedDbItem = service.update(dbItem.getId(), dbItem.getTitle(), dbItem.getCoverArt(),
                    dbItem.getDeveloperSet(), dbItem.getGenreSet(), dbItem.getPlatformSet(), dbItem.getPublisherSet(), 
                    dbItem.getRegionSet());

            GameOutputDTO result = IGameOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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
            String message = "Game deleted correctly";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        } else {
            String message = "Game not deleted";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomApiResponse<>(500, message, null));
        }

    }


    @PostMapping(value = "/upload/{title}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("title") String title, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String namefile = storageService.save(file);
            message = "" + namefile;

            Game aux = service.findByTitle(title);
            aux.setCoverArt(namefile);
            Game result = service.updateCoverArt(aux.getId(), aux.getCoverArt());
            GameOutputDTO dto = IGameOutputDTOMapper.INSTANCE.toDTO(result);

            return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse<>(200, message, dto));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename()
                    + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CustomApiResponse<>(417, message, null));
        }
    }

    @GetMapping("/img/{filename}")
    public ResponseEntity<?> getFiles(@PathVariable String filename) {
        Resource resource = storageService.get(filename);

        String contentType = null;
        try {
            contentType = URLConnection.guessContentTypeFromStream(resource.getInputStream());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        String headerValue = "attachment";
        filename="" +
                resource.getFilename() + "";
        return ResponseEntity.
                ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        org.springframework.http.HttpHeaders.
                                CONTENT_DISPOSITION,
                        headerValue
                )
                .body(resource);
    }
}