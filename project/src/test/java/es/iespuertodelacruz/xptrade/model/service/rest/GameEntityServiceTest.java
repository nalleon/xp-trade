package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.repository.IGameEntityRepository;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameEntityServiceTest extends TestUtilities {
    @Mock
    IGameEntityRepository repositoryMock;

    @InjectMocks
    GameEntityService service;


    Game item;

    Genre genre;
    Region region;
    Publisher publisher;
    Developer developer;
    Platform platform;
    Set<Developer> developerSet;
    Set<Genre> genreSet;
    Set<Platform> platformSet;
    Set<Publisher> publisherSet;
    Set<Region> regionSet;

    @BeforeEach
    public void beforeEach() {
        genre = new Genre(NAME);
        region = new Region(NAME);
        publisher = new Publisher(NAME);
        developer = new Developer(NAME);
        platform = new Platform(NAME);

        genre.setId(ID);
        region.setId(ID);
        publisher.setId(ID);
        developer.setId(ID);
        platform.setId(ID);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));

        item =  new Game(TITLE, COVER_ART, developerSet, genreSet, platformSet, publisherSet, regionSet);
        item.setId(ID);

        MockitoAnnotations.openMocks(this);
        service = new GameEntityService();
        service.setRepository(repositoryMock);

    }
    @Test
    void getAllTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByTitleNullTest() {
        Assertions.assertNull(service.findByTitle(TITLE), MESSAGE_ERROR);
    }

    @Test
    void getAllByRegionNullTest() {
        Assertions.assertNull(service.findAllByRegion(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByGenreNullTest() {
        Assertions.assertNull(service.findAllByGenre(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByPlatformNullTest() {
        Assertions.assertNull(service.findAllByPlatform(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByDeveloperNullTest() {
        Assertions.assertNull(service.findAllByDeveloper(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByPublisherNullTest() {
        Assertions.assertNull(service.findAllByPublisher(null), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new GameEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByTitleTest() {
        when(repositoryMock.findByTitle(TITLE)).thenReturn(Optional.of(new GameEntity()));
        Assertions.assertNotNull(service.findByTitle(TITLE), MESSAGE_ERROR);
    }


    @Test
    void getAllByDeveloperTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByDeveloper(developer.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByDeveloper(developer), MESSAGE_ERROR);
    }

    @Test
    void getAllByPublisherTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByPublisher(publisher.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByPublisher(publisher), MESSAGE_ERROR);
    }

    @Test
    void getAllByGenreTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByGenre(genre.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByGenre(genre), MESSAGE_ERROR);
    }

    @Test
    void getAllByRegionTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByRegion(region.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByRegion(region), MESSAGE_ERROR);
    }

    @Test
    void getAllByPlatformTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByPlatform(platform.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByPlatform(platform), MESSAGE_ERROR);
    }


    @Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        when(repositoryMock.save(any(GameEntity.class))).thenReturn(new GameEntity());

        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }

//    @Test
//    void updateExceptionTest() throws Exception {
//        when(repositoryMock.findUserByName(NAME)).thenThrow(new RuntimeException());
//        Assertions.assertThrows(RuntimeException.class, () -> service.update(new User(1)), MESSAGE_ERROR);
//    }


    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.save(any(GameEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new Game(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(new GameEntity()));
        Assertions.assertNotNull(service.update(item), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertNull(service.update(null), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.empty());
        Assertions.assertNull(service.update(item), MESSAGE_ERROR);
    }

//    @Test
//    void updateExceptionTest() throws Exception {
//        Game item = new Game();
//        item.setId(1);
//        item.setName(NAME);
//        when(repositoryMock.findByName(item.getName())).thenReturn(Optional.empty());
//        Assertions.assertNull(service.update(item), MESSAGE_ERROR);
//    }

    @Test
    void deleteAdminTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new GameEntity(1)));
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteNotFoundTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.empty());
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteTest() {
        when(repositoryMock.deleteEntityById(2)).thenReturn(2);
        Assertions.assertTrue(service.delete(2), MESSAGE_ERROR);
    }


    @Test
    void deleteNonExistentTest() {
        when(repositoryMock.deleteEntityById(1)).thenReturn(0);
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void updateCoverPictureTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(new GameEntity()));

        Assertions.assertNotNull(service.updateCoverArt(item), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundCoverTest() throws Exception {

        when(repositoryMock.findById(item.getId())).thenReturn(Optional.empty());

        Assertions.assertNull(service.updateCoverArt(item), MESSAGE_ERROR);
    }

    @Test
    void updatePictureFalseTest() throws Exception {
        Assertions.assertNull(service.updateCoverArt(item), MESSAGE_ERROR);
    }

}

