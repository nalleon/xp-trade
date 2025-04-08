package es.iespuertodelacruz.xptrade.controller.v2;

import es.iespuertodelacruz.xptrade.controllers.v2.GameRESTControllerV2;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.service.*;
import es.iespuertodelacruz.xptrade.dto.output.*;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameRESTControllerV2Test extends MapperDTOHelper {
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
    GameRESTControllerV2 controller;


    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);

        controller = new GameRESTControllerV2();
        controller.setService(serviceMock);
        controller.setDeveloperService(serviceDeveloperMock);
        controller.setGenreService(serviceGenreMock);
        controller.setRegionService(serviceRegionMock);
        controller.setPlatformService(servicePlatformMock);
        controller.setPublisherService(servicePublisherMock);


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

        gameOutputDTO = new GameOutputDTO(ID, TITLE, COVER_ART,new HashSet<>(Collections.singletonList(developerOutputDTO)),
                new HashSet<>(Collections.singletonList(genreOutputDTO)),
                new HashSet<>(Collections.singletonList(platformOutputDTO)),
                new HashSet<>(Collections.singletonList(publisherOutputDTO)),
                new HashSet<>(Collections.singletonList(regionOutputDTO))
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

    @Test
    void getOneByTitleTest() {
        when(serviceMock.findByTitle(TITLE)).thenReturn(new Game(TITLE));
        Assertions.assertNotNull(controller.getByTitle(TITLE), MESSAGE_ERROR);
    }
}
