package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.GameRESTController;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.service.*;
import es.iespuertodelacruz.xptrade.dto.input.*;
import es.iespuertodelacruz.xptrade.dto.output.*;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.model.repository.IGameEntityRepository;
import es.iespuertodelacruz.xptrade.model.service.rest.GameEntityService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameRESTControllerV3Test extends TestUtilities {
    public static final TagOutputDTO TAG_OUTPUT_DTO = new TagOutputDTO(ID, NAME);
    @Mock
    GameService serviceMock;

    @Mock
    FileStorageService storageServiceMock;

    @Mock
    DeveloperService serviceDeveloperMock;

    @Mock
    GenreService serviceGenreMock;

    @Mock
    PlatformService servicePlatformMock;

    @Mock
    PublisherService servicePublisherMock;

    @Mock
    RegionService serviceRegionMock;
    @InjectMocks
    GameEntityService serviceEntityMock;
    @Mock
    IGameEntityRepository repositoryMock;


    @InjectMocks
    GameRESTController controller;

    public UserDTO userDTO;
    public User userDomain;

    public GenreOutputDTO genreOutputDTO;
    public Genre genreDomain;
    public List<GenreOutputDTO> genreOutputDTOList;
    public List<Genre> genreDomains;

    public PublisherOutputDTO publisherOutputDTO;
    public Publisher publisherDomain;
    public List<PublisherOutputDTO> publisherOutputDTOList;
    public List<Publisher> publisherDomains;

    public DeveloperOutputDTO developerOutputDTO;
    public Developer developerDomain;
    public List<DeveloperOutputDTO> developerOutputDTOList;
    public List<Developer> developerDomains;

    public PlatformOutputDTO platformOutputDTO;
    public Platform platformDomain;
    public List<PlatformOutputDTO> platformOutputDTOList;
    public List<Platform> platformDomains;

    public RegionOutputDTO regionOutputDTO;
    public Region regionDomain;
    public List<RegionOutputDTO> regionOutputDTOList;
    public List<Region> regionDomains;

    public GameOutputDTO gameOutputDTO;
    public Game gameDomain;


    public GameInputDTO gameInputDTO;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);

        serviceEntityMock.setRepository(repositoryMock);
        serviceMock.setRepository(serviceEntityMock);
        controller.setService(serviceMock);
        controller.setDeveloperService(serviceDeveloperMock);
        controller.setGenreService(serviceGenreMock);
        controller.setRegionService(serviceRegionMock);
        controller.setPlatformService(servicePlatformMock);
        controller.setPublisherService(servicePublisherMock);
        controller.setStorageService(storageServiceMock);

        genreDomain = new Genre();
        genreDomain.setId(ID);
        genreDomain.setName(NAME);

        genreOutputDTOList = new ArrayList<>();
        genreOutputDTOList.add(genreOutputDTO);

        genreDomains = new ArrayList<>();
        genreDomains.add(genreDomain);

        developerOutputDTO = new DeveloperOutputDTO(ID, NAME);

        developerDomain = new Developer();
        developerDomain.setId(ID);
        developerDomain.setName(NAME);

        developerDomains = new ArrayList<>();
        developerDomains.add(developerDomain);

        developerOutputDTOList = new ArrayList<>();
        developerOutputDTOList.add(developerOutputDTO);

        regionOutputDTO = new RegionOutputDTO(ID, NAME);

        regionDomain = new Region();
        regionDomain.setId(ID);
        regionDomain.setName(NAME);

        regionOutputDTOList = new ArrayList<>();
        regionOutputDTOList.add(regionOutputDTO);

        regionDomains = new ArrayList<>();
        regionDomains.add(regionDomain);

        publisherOutputDTO = new PublisherOutputDTO(ID, NAME);


        publisherDomain = new Publisher();
        publisherDomain.setId(ID);
        publisherDomain.setName(NAME);

        publisherDomains = new ArrayList<>();
        publisherDomains.add(publisherDomain);

        publisherOutputDTOList = new ArrayList<>();
        publisherOutputDTOList.add(publisherOutputDTO);

        platformOutputDTO = new PlatformOutputDTO(ID, NAME);

        platformDomain = new Platform();
        platformDomain.setId(ID);
        platformDomain.setName(NAME);

        platformDomains = new ArrayList<>();
        platformDomains.add(platformDomain);

        platformOutputDTOList = new ArrayList<>();
        platformOutputDTOList.add(platformOutputDTO);

        gameOutputDTO = new GameOutputDTO(ID, TITLE, COVER_ART, SLUG, RATING, RELEASED,
                new HashSet<>(Collections.singletonList(TAG_OUTPUT_DTO)),
                new HashSet<>(Collections.singletonList(developerOutputDTO)),
                new HashSet<>(Collections.singletonList(genreOutputDTO)),
                new HashSet<>(Collections.singletonList(platformOutputDTO)),
                new HashSet<>(Collections.singletonList(publisherOutputDTO))
        );

        gameDomain = new Game();
        gameDomain.setId(ID);
        gameDomain.setTitle(TITLE);
        gameDomain.setCoverArt(COVER_ART);
        gameDomain.setReleased(RELEASED);
        gameDomain.setRating(RATING);
        gameDomain.setPublisherSet(new HashSet<>(Collections.singletonList(publisherDomain)));
        gameDomain.setDeveloperSet(new HashSet<>(Collections.singletonList(developerDomain)));
        gameDomain.setPlatformSet(new HashSet<>(Collections.singletonList(platformDomain)));
        gameDomain.setGenreSet(new HashSet<>(Collections.singletonList(genreDomain)));
        gameDomain.setTagSet(new HashSet<>());

        gameInputDTO = new GameInputDTO(TITLE, COVER_ART, SLUG, RATING, RELEASED,
                new HashSet<>(Collections.singletonList(new TagInputDTO(NAME))),
                new HashSet<>(Collections.singletonList(new DeveloperInputDTO(NAME))),
                new HashSet<>(Collections.singletonList(new GenreInputDTO(NAME))),
                new HashSet<>(Collections.singletonList(new PlatformInputDTO(NAME))),
                new HashSet<>(Collections.singletonList(new PublisherInputDTO(NAME)))
        );
    }
    @Test
    void getAllTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }

    @Test
    void getAllEmptyTest() {
        when(serviceMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAll().getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllGenreTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceGenreMock.findByName(anyString())).thenReturn(new Genre());
        when(serviceMock.findAllByGenre(new Genre())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByGenre(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllGenreNoFilterTest() {
        when(serviceGenreMock.findByName(anyString())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByGenre(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllGenreEmptyTest() {
        when(serviceGenreMock.findByName(anyString())).thenReturn(new Genre());
        when(serviceMock.findAllByPlatform(new Platform())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByGenre(NAME).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllPlatformTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(servicePlatformMock.findByName(anyString())).thenReturn(new Platform());
        when(serviceMock.findAllByPlatform(new Platform())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByPlatform(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllPlatformNoFilterTest() {
        when(servicePlatformMock.findByName(anyString())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByPlatform(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllPlatformEmptyTest() {
        when(servicePlatformMock.findByName(anyString())).thenReturn(new Platform());
        when(serviceMock.findAllByPlatform(new Platform())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByPlatform(NAME).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllDeveloperTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceDeveloperMock.findByName(anyString())).thenReturn(new Developer());
        when(serviceMock.findAllByDeveloper(new Developer())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByDeveloper(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllDeveloperNoFilterTest() {
        when(serviceDeveloperMock.findByName(anyString())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByDeveloper(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllDeveloperEmptyTest() {
        when(serviceDeveloperMock.findByName(anyString())).thenReturn(new Developer());
        when(serviceMock.findAllByPlatform(new Platform())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByDeveloper(NAME).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void getAllPublisherTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(servicePublisherMock.findByName(anyString())).thenReturn(new Publisher());
        when(serviceMock.findAllByPublisher(new Publisher())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByPublisher(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllPublisherNoFilterTest() {
        when(servicePublisherMock.findByName(anyString())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByPublisher(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllPublisherEmptyTest() {
        when(servicePublisherMock.findByName(anyString())).thenReturn(new Publisher());
        when(serviceMock.findAllByPlatform(new Platform())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByPublisher(NAME).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void getAllRegionTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceRegionMock.findByName(anyString())).thenReturn(new Region());
        when(serviceMock.findAllByRegion(new Region())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByRegion(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllRegionNoFilterTest() {
        when(serviceRegionMock.findByName(anyString())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByRegion(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllRegionEmptyTest() {
        when(serviceRegionMock.findByName(anyString())).thenReturn(new Region());
        when(serviceMock.findAllByPlatform(new Platform())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByRegion(NAME).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Game(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneNotFoundTest() {
        when(serviceMock.findById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getById(1).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getOneByTitleTest() {
        when(serviceMock.findByTitle(TITLE)).thenReturn(new Game(TITLE));
        Assertions.assertNotNull(controller.getByTitle(TITLE), MESSAGE_ERROR);
    }

    @Test
    void getOneByTitleNotFoundTest() {
        when(serviceMock.findByTitle(anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getByTitle(TITLE).getStatusCode(), MESSAGE_ERROR);
    }

   @Test
    void addTest() {
        when(serviceMock.add(gameInputDTO.title(), gameInputDTO.coverArt(), gameInputDTO.slug(),gameInputDTO.rating(),
                gameDomain.getReleased(), gameDomain.getTagSet(),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet())).thenReturn(gameDomain);

       Assertions.assertEquals(HttpStatus.CREATED, controller.add(gameInputDTO).getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addExceptionTest() {
        when(serviceMock.add(
                anyString(), anyString(), anyString(),
                anyInt(), anyString(), anySet(),
                anySet(), anySet(), anySet(),
                anySet())
        ).thenThrow(new RuntimeException());

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, controller.add(gameInputDTO).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteTest() {
        when(serviceMock.findById(any(Integer.class))).thenReturn(gameDomain);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        when(serviceMock.findById(any(Integer.class))).thenReturn(gameDomain);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        when(serviceMock.findById(any(Integer.class))).thenReturn(gameDomain);
        when(serviceMock.add(
                anyString(), anyString(), anyString(),
                anyInt(), anyString(), anySet(),
                anySet(), anySet(), anySet(),
                anySet())
        ).thenReturn(new Game());

        when(serviceMock.update(anyInt(),
                anyString(), anyString(), anyString(),
                anyInt(), anyString(), anySet(),
                anySet(), anySet(), anySet(),
                anySet())
        ).thenReturn(gameDomain);

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, gameInputDTO);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() {
      ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, null);
      Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        when(serviceMock.findById(any(Integer.class))).thenReturn(null);

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, gameInputDTO);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {

        when(serviceMock.findById(any(Integer.class))).thenReturn(gameDomain);

        when(serviceMock.update(
                anyInt(),
                anyString(), anyString(), anyString(),
                anyInt(), anyString(), anySet(),
                anySet(), anySet(), anySet(),
                anySet())
        ).thenThrow(new RuntimeException());

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, controller.update(1,gameInputDTO).getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void updateCoverTest() {
        MultipartFile mockFile = mock(MultipartFile.class);

        when(serviceMock.findByTitle(anyString())).thenReturn(gameDomain);
        when(storageServiceMock.save(mockFile)).thenReturn("gameDomain");


        when(serviceMock.updateCoverArt(anyInt(),
                anyString())
        ).thenReturn(gameDomain);


        Assertions.assertEquals(HttpStatus.OK, controller.uploadFile(NAME, mockFile).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateCoverNullTitleTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.uploadFile(null, null).getStatusCode(), MESSAGE_ERROR);

    }

    @Test
    void updateCoverNullFileTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.uploadFile(NAME, null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateCoverNotFoundTest() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(serviceMock.findByTitle(anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.uploadFile(NAME, mockFile).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateCoverExceptionTest() throws Exception {
        MultipartFile mockFile = mock(MultipartFile.class);

        when(serviceMock.findByTitle(anyString())).thenReturn(gameDomain);
        when(storageServiceMock.save(mockFile)
        ).thenThrow(new RuntimeException());

        when(serviceMock.updateCoverArt(anyInt(),
                anyString())
        ).thenThrow(new RuntimeException());

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, controller.uploadFile(NAME, mockFile).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getGameCoverNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.getFiles(null).getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void getGameCoverEmptyTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.getFiles("").getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void getGameCoverTest() throws IOException {
        Resource resource = mock(Resource.class);
        InputStream mockInputStream = new ByteArrayInputStream("dummy content".getBytes());
        when(resource.getInputStream()).thenReturn(mockInputStream);
        when(resource.getFilename()).thenReturn("dummy.txt");
        when(storageServiceMock.get(anyString())).thenReturn(resource);

        Assertions.assertEquals(HttpStatus.OK, controller.getFiles("dummy.txt").getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getGameCoverNullContentTypeTest() throws IOException {
        Resource resource = mock(Resource.class);
        InputStream mockInputStream = new ByteArrayInputStream("dummy content".getBytes());
        when(resource.getInputStream()).thenReturn(mockInputStream);
        when(resource.getFilename()).thenReturn("application/octet-stream");
        when(storageServiceMock.get(anyString())).thenReturn(resource);

        Assertions.assertEquals(HttpStatus.OK, controller.getFiles("dummy.txt").getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getGameCoverExceptionTest() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenThrow(new IOException());
        when(resource.getFilename()).thenReturn("dummy.txt");
        when(storageServiceMock.get(anyString())).thenReturn(resource);

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, controller.getFiles("dummy.txt").getStatusCode(), MESSAGE_ERROR);
    }
}
