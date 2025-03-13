package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameRepository;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameServiceTest extends TestUtilities {
    @Mock
    IGameRepository repositoryMock;

    @InjectMocks
    GameService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new GameService();
        service.setRepository(repositoryMock);
    }

    @Test
    void findAllByGenreTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        list.add(new Game());
        list.add(new Game());
        Mockito.when(repositoryMock.findAllByGenre(new Genre())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByGenre(new Genre()), MESSAGE_ERROR);
    }

    @Test
    void findAllByDeveloperTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        list.add(new Game());
        list.add(new Game());
        Mockito.when(repositoryMock.findAllByDeveloper(new Developer())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByDeveloper(new Developer()), MESSAGE_ERROR);
    }

    @Test
    void findAllByPlatformTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        list.add(new Game());
        list.add(new Game());
        Mockito.when(repositoryMock.findAllByPlatform(new Platform())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByPlatform(new Platform()), MESSAGE_ERROR);
    }

    @Test
    void findAllByPublisherTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        list.add(new Game());
        list.add(new Game());
        Mockito.when(repositoryMock.findAllByPublisher(new Publisher())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByPublisher(new Publisher()), MESSAGE_ERROR);
    }

    @Test
    void findAllByRegionTest() {
        List<Game> list = new ArrayList<>();
        list.add(new Game());
        list.add(new Game());
        list.add(new Game());
        Mockito.when(repositoryMock.findAllByRegion(new Region())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByRegion(new Region()), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(new Game());
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByTitleNullTest() {
        Assertions.assertNull(service.findByTitle(NAME), MESSAGE_ERROR);
    }

    @Test
    void getByTitleTest() {
        Mockito.when(repositoryMock.findByTitle(NAME)).thenReturn(new Game());
        Assertions.assertNotNull(service.findByTitle(NAME), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        Mockito.when(repositoryMock.save(Mockito.any(Game.class))).thenReturn(new Game());
        Set<Developer> developerSet = new HashSet<>();
        Set<Genre> genreSet = new HashSet<>();
        Set<Platform> platformSet = new HashSet<>();
        Set<Publisher> publisherSet = new HashSet<>();
        Set<Region> regionSet = new HashSet<>();
        Assertions.assertNotNull(service.add(TITLE, COVER_ART, developerSet,
                genreSet, platformSet, publisherSet, regionSet), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.add(null, null, null, null, null,
                null, null), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenThrow(new RuntimeException("Database error"));
        Set<Developer> developerSet = new HashSet<>();
        Set<Genre> genreSet = new HashSet<>();
        Set<Platform> platformSet = new HashSet<>();
        Set<Publisher> publisherSet = new HashSet<>();
        Set<Region> regionSet = new HashSet<>();
        Assertions.assertNull(service.update(1, TITLE, COVER_ART, developerSet,
                genreSet, platformSet, publisherSet, regionSet), MESSAGE_ERROR);
    }
    @Test
    void updateTest() throws Exception {
        Mockito.when(repositoryMock.update(Mockito.any(Game.class))).thenReturn(new Game());
        Set<Developer> developerSet = new HashSet<>();
        Set<Genre> genreSet = new HashSet<>();
        Set<Platform> platformSet = new HashSet<>();
        Set<Publisher> publisherSet = new HashSet<>();
        Set<Region> regionSet = new HashSet<>();
        Assertions.assertNotNull(service.update(1,TITLE, COVER_ART, developerSet,
                genreSet, platformSet, publisherSet, regionSet), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() throws Exception {
        Assertions.assertNull(service.update(0, null, null, null, null, null,
                null, null), MESSAGE_ERROR);
    }

    @Test
    void deleteTest() {
        Mockito.when(repositoryMock.delete(2)).thenReturn(true);
        Assertions.assertTrue(service.delete(2), MESSAGE_ERROR);
    }


    @Test
    void deleteNonExistentTest() {
        Mockito.when(repositoryMock.delete(1)).thenReturn(false);
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteFailTest() {
        Mockito.when(repositoryMock.delete(2)).thenReturn(false);
        Assertions.assertFalse(service.delete(2), MESSAGE_ERROR);
    }
}
