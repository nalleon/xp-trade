package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.GameRESTController;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.service.*;
import es.iespuertodelacruz.xptrade.dto.*;
import es.iespuertodelacruz.xptrade.model.repository.IGameEntityRepository;
import es.iespuertodelacruz.xptrade.model.service.rest.GameEntityService;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameRESTControllerV3Test extends MapperDTOHelper {
    @Mock
    GameService serviceMock;

    @Mock
    DeveloperService serviceDeveloperMock;

    @Mock
    GenreService serviceGenreMock;

    @Mock
    FileStorageService serviceStorageMock;

    @Mock
    PlatformService servicePlatformMock;

    @Mock
    PublisherService servicePublisherMock;

    @Mock
    RegionService serviceRegionMock;

    @InjectMocks
    GameRESTController controller;


    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);

        controller = new GameRESTController();
        controller.setService(serviceMock);
        controller.setDeveloperService(serviceDeveloperMock);
        controller.setGenreService(serviceGenreMock);
        controller.setStorageService(serviceStorageMock);
        controller.setRegionService(serviceRegionMock);
        controller.setPlatformService(servicePlatformMock);
        controller.setPublisherService(servicePublisherMock);


        genreDomain = new Genre();
        genreDomain.setId(ID);
        genreDomain.setName(NAME);

        genreDTOList = new ArrayList<>();
        genreDTOList.add(genreDTO);

        genreDomains = new ArrayList<>();
        genreDomains.add(genreDomain);

        developerDTO = new DeveloperDTO(ID, NAME);

        developerDomain = new Developer();
        developerDomain.setId(ID);
        developerDomain.setName(NAME);

        developerDomains = new ArrayList<>();
        developerDomains.add(developerDomain);

        developerDTOList = new ArrayList<>();
        developerDTOList.add(developerDTO);

        regionDTO = new RegionDTO(ID, NAME);

        regionDomain = new Region();
        regionDomain.setId(ID);
        regionDomain.setName(NAME);

        regionDTOList = new ArrayList<>();
        regionDTOList.add(regionDTO);

        regionDomains = new ArrayList<>();
        regionDomains.add(regionDomain);

        publisherDTO = new PublisherDTO(ID, NAME);


        publisherDomain = new Publisher();
        publisherDomain.setId(ID);
        publisherDomain.setName(NAME);

        publisherDomains = new ArrayList<>();
        publisherDomains.add(publisherDomain);

        publisherDTOList = new ArrayList<>();
        publisherDTOList.add(publisherDTO);

        platformDTO = new PlatformDTO(ID, NAME);

        platformDomain = new Platform();
        platformDomain.setId(ID);
        platformDomain.setName(NAME);

        platformDomains = new ArrayList<>();
        platformDomains.add(platformDomain);

        platformDTOList = new ArrayList<>();
        platformDTOList.add(platformDTO);

        gameDTO = new GameDTO(ID, TITLE, COVER_ART,new HashSet<>(Collections.singletonList(developerDTO)),
                new HashSet<>(Collections.singletonList(genreDTO)),
                new HashSet<>(Collections.singletonList(platformDTO)),
                new HashSet<>(Collections.singletonList(publisherDTO)),
                new HashSet<>(Collections.singletonList(regionDTO))
        );

        gameDomain = new Game();
        gameDomain.setId(ID);
        gameDomain.setTitle(TITLE);
        gameDomain.setCoverArt(COVER_ART);
        gameDomain.setPublisherSet(new HashSet<>(Collections.singletonList(publisherDomain)));
        gameDomain.setDeveloperSet(new HashSet<>(Collections.singletonList(developerDomain)));
        gameDomain.setPlatformSet(new HashSet<>(Collections.singletonList(platformDomain)));
        gameDomain.setRegionSet(new HashSet<>(Collections.singletonList(regionDomain)));
        gameDomain.setGenreSet(new HashSet<>(Collections.singletonList(genreDomain)));

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
    void getAllGenreTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceMock.findAllByGenre(genreDomain)).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByGenre(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllPlatformTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceMock.findAllByPlatform(platformDomain)).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByPlatform(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllDeveloperTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceMock.findAllByDeveloper(developerDomain)).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByDeveloper(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllPublisherTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceMock.findAllByPublisher(publisherDomain)).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByPublisher(NAME), MESSAGE_ERROR);
    }

    @Test
    void getAllRegionTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game(1));
        list.add(new Game(2));
        list.add(new Game(3));
        when(serviceMock.findAllByRegion(regionDomain)).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByRegion(NAME), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Game(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    //@Test
    void addTest() {
        when(serviceMock.add(any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(new Game());

        ResponseEntity responseEntity = controller.add(gameDTO);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
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
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    // @Test
    void updateTest() {
        when(serviceMock.findById(any(Integer.class))).thenReturn(gameDomain);
        when(serviceMock.add(any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(new Game());


        when(serviceMock.update(any(Integer.class),any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(gameDomain);

        ResponseEntity responseEntity = controller.update(1, gameDTO);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    //@Test
    void updateNotFoundTest() {

        when(serviceMock.add(any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(new Game());

        when(serviceMock.update(any(Integer.class),any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(gameDomain);

        ResponseEntity responseEntity = controller.update(1, gameDTO);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    //@Test
    void updateInvalidDataTest() {

        when(serviceMock.add(any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(new Game());

        ResponseEntity responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    //@Test
    void updateExceptionTest() throws Exception {

        when(serviceMock.findById(any(Integer.class))).thenReturn(gameDomain);
        when(serviceMock.add(any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenReturn(new Game());

        when(serviceMock.update(any(Integer.class),any(String.class), any(String.class),
                gameDomain.getDeveloperSet(), gameDomain.getGenreSet(), gameDomain.getPlatformSet(),
                gameDomain.getPublisherSet(), gameDomain.getRegionSet())).thenThrow(new RuntimeException("Database error"));

        ResponseEntity responseEntity = controller.update(1, gameDTO);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
