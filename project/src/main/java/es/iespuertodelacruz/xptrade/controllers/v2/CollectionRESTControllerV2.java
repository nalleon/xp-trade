package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.*;
import es.iespuertodelacruz.xptrade.dto.input.CollectionInputDTO;
import es.iespuertodelacruz.xptrade.dto.input.GameCollectionInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.GameCollectionOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.input.ICollectionInputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.input.IGameCollectionInputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.ICollectionOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameCollectionOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import es.iespuertodelacruz.xptrade.shared.security.CustomUserDetails;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/collections")
@Tag(name="v2 - Collection ", description = "For authenticated users")
public class CollectionRESTControllerV2 {

    public static final String ADMIN = "ROLE_ADMIN";
    /**
     * Properties
     */
    private ICollectionService service;
    private IGameCollectionService gameCollectionService;
    private IGameService gameService;
    private IUserService userService;
    private IGenericService<Platform, Integer, String> platformService;
    private IGenericService<Region, Integer, String> regionService;

    /**
     * Setters of the collection service
     * @param service of the collection
     */
    @Autowired
    public void setService(ICollectionService service) {
        this.service = service;
    }

    /**
     * Setters of the collection service
     * @param gameCollectionService of the collection
     */
    @Autowired
    public void setGameCollectionService(IGameCollectionService gameCollectionService) {
        this.gameCollectionService = gameCollectionService;
    }

    /**
     * Setters of the collection service
     * @param gameService of the collection
     */
    @Autowired
    public void setGameService(IGameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Setters of the collection service
     * @param userService of the collection
     */
    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Setters of the collection service
     * @param platformService of the collection
     */
    @Autowired
    public void setPlatformService(IGenericService<Platform, Integer, String> platformService) {
        this.platformService = platformService;
    }

    /**
     * Setters of the collection service
     * @param regionService of the collection
     */
    @Autowired
    public void setRegionService(IGenericService<Region, Integer, String> regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CollectionOutputDTO> filteredList = ICollectionOutputDTOMapper.INSTANCE.toDTOList(service.findAll());

        if (filteredList.isEmpty()) {
            String message = "There are no collections";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    /**
     * Endpoint to get all collections of a user
     * @param username the username of the user
     * @return a list of collections
     */
    @GetMapping("/users/{username}")
    public ResponseEntity<?> getAllByUser(@PathVariable String username) {
        User filter = userService.findByUsername(username);

        if(filter == null){
            String message = "Filter does NOT exist";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, null));
        }

        Collection collectionDb = service.add(filter);

        if (collectionDb == null) {
            String message = "There are no collections";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, collectionDb));
        }

        List<GameCollectionOutputDTO> gameCollectionList = IGameCollectionOutputDTOMapper.INSTANCE.toDTOList(gameCollectionService.findByCollection(collectionDb));

        CollectionOutputDTO collectionOutputDTO = new CollectionOutputDTO(collectionDb.getId(),
                gameCollectionList, IUserDTOMapper.INSTANCE.toDTO(filter));

        String message = "Collection successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, collectionOutputDTO));
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Collection aux = service.findById(id);
        if (aux != null){
            CollectionOutputDTO dto = ICollectionOutputDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<CollectionOutputDTO> response =
                    new CustomApiResponse<>(200, "Collection OK", dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomApiResponse<CollectionOutputDTO> errorResponse = new CustomApiResponse<>(204, "Collection NOT OK", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }




    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> add(@RequestBody CollectionInputDTO dto) {
        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "Item cannot be null", null));
        }

        try {
            Collection aux = ICollectionInputDTOMapper.INSTANCE.toDomain(dto);


            User userDb = userService.findByUsername(aux.getUser().getUsername());

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            Collection dbItem = service.add(userDb);

            CollectionOutputDTO result = ICollectionOutputDTOMapper.INSTANCE.toDTO(dbItem);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Item created successfully", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to add item", null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody CollectionInputDTO dto) {

        if (dto == null) {
            return ResponseEntity.badRequest().build();
        }

        Collection dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "Item not found", null));
        }

        try {

            Collection aux = ICollectionInputDTOMapper.INSTANCE.toDomain(dto);

            User userDb = userService.findByUsername(aux.getUser().getUsername());

            if(userDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }


            Collection updatedDbItem = service.update(id, userDb);

            if(updatedDbItem == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new CustomApiResponse<>(204, "Item does not exists", null));
            }


            CollectionOutputDTO result = ICollectionOutputDTOMapper.INSTANCE.toDTO(updatedDbItem);

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

    @PostMapping("/{username}")
    public ResponseEntity<?> addGameToCollection(@RequestBody GameCollectionInputDTO dto, @PathVariable String username) {

        if (dto == null) {
            return ResponseEntity.badRequest()
                    .body(new CustomApiResponse<>(400, "Item cannot be null", null));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String authUsername = userDetails.getUsername();

        if (!Objects.equals(authUsername, username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomApiResponse<>(401, "Unauthorized", null));
        }

        try {
            GameCollection aux = IGameCollectionInputDTOMapper.INSTANCE.toDomain(dto);


            Game gameDb = gameService.add(aux.getGame().getTitle(), aux.getGame().getCoverArt(), aux.getGame().getSlug(),
                    aux.getGame().getRating(), aux.getGame().getReleased(), aux.getGame().getTagSet(),
                    aux.getGame().getDeveloperSet(), aux.getGame().getGenreSet(), aux.getGame().getPlatformSet(),
                    aux.getGame().getPublisherSet());

            if(gameDb == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "Item cannot be null", null));
            }

            User filter = userService.findByUsername(username);

            if(filter == null){
                return ResponseEntity.badRequest()
                        .body(new CustomApiResponse<>(400, "User does not exist", null));
            }

            Collection collectionDb = service.add(filter);

            if (collectionDb == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new CustomApiResponse<>(204, "Collection cannot be null", null));
            }

            GameCollection gameCollection = gameCollectionService.add(gameDb, collectionDb,
                    saveOrGetRegion(aux.getRegion().getName()), saveOrGetPlatform(aux.getPlatform().getName()));

            if (gameCollection == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new CustomApiResponse<>(204, "GameCollection cannot be null", null));
            }

            GameCollectionOutputDTO result = IGameCollectionOutputDTOMapper.INSTANCE.toDTO(gameCollection);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomApiResponse<>(201, "Item created successfully", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to add item", null));
        }
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGameFromCollection(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        User userDb = userService.findByUsername(username);

        GameCollection dbItem = gameCollectionService.findById(id);

        if (userDb.getId() != dbItem.getCollection().getUser().getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomApiResponse<>(401, "Unauthorized", null));
        }

        boolean deleted = gameCollectionService.delete(id);

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

    public Platform saveOrGetPlatform(String name) {
        Platform platform = platformService.findByName(name);

        if (platform == null) {
            platform = platformService.add(name);
        }

        return platform;
    }

    public Region saveOrGetRegion(String name) {
        Region region = regionService.findByName(name);

        if (region == null) {
            region = regionService.add(name);
        }

        return region;
    }
}
