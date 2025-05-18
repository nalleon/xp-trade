package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.mapper.entity.IGameEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import es.iespuertodelacruz.xptrade.model.entities.FavoriteEntity;
import es.iespuertodelacruz.xptrade.model.repository.IFavoriteEntityRepository;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FavoriteEntityServiceTest extends TestUtilities {
    @Mock
    IFavoriteEntityRepository repositoryMock;

    @InjectMocks
    FavoriteEntityService service;

    User user;

    Favorite item;

    Game game;
    Genre genre;
    Tag tag;
    Publisher publisher;
    Developer developer;
    Platform platform;
    Set<Developer> developerSet;
    Set<Genre> genreSet;
    Set<Platform> platformSet;
    Set<Publisher> publisherSet;
    Set<Tag> tagSet;

    @BeforeEach
    public void beforeEach() {
        genre = new Genre(NAME);
        tag = new Tag(NAME);
        publisher = new Publisher(NAME);
        developer = new Developer(NAME);
        platform = new Platform(NAME);

        genre.setId(ID);
        tag.setId(ID);
        publisher.setId(ID);
        developer.setId(ID);
        platform.setId(ID);
        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        tagSet = new HashSet<>(Collections.singletonList(tag));

        game = new Game(TITLE, COVER_ART,SLUG,  RATING, RELEASED, tagSet, developerSet, genreSet, platformSet, publisherSet);

        game.setId(ID);
        user = new User(ID);
        user.setUsername(NAME);

        item = new Favorite();
        item.setId(ID);
        item.setUser(user);
        item.setGame(game);

        MockitoAnnotations.openMocks(this);
        service = new FavoriteEntityService();
        service.setRepository(repositoryMock);

    }
    @Test
    void getAllTest() {
        List<FavoriteEntity> list = new ArrayList<>();
        list.add(new FavoriteEntity());
        when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByIdNameNullTest() {
        Assertions.assertNull(service.findAllByUser(null), MESSAGE_ERROR);
    }

    @Test
    void getByIdGameNullTest() {
        Assertions.assertNull(service.findAllBySubject(null), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new FavoriteEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }
    @Test
    void checkIfExistsTest() {
        when(repositoryMock.checkIfInFavorite(anyString(), anyString())).thenReturn(Optional.of(new FavoriteEntity()));
        Assertions.assertNotNull(service.checkIfExists(user, game), MESSAGE_ERROR);
    }
    @Test
    void checkIfExistsNullTest() {
        Assertions.assertNull(service.checkIfExists(user, game), MESSAGE_ERROR);
    }

    @Test
    void getByNameTest() {
        List<FavoriteEntity> list = new ArrayList<>();
        list.add(new FavoriteEntity());
        when(repositoryMock.findAllByUser(user.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByUser(user), MESSAGE_ERROR);
    }

    @Test
    void getByGameTest() {
        List<FavoriteEntity> list = new ArrayList<>();
        list.add(new FavoriteEntity());
        when(repositoryMock.findAllByGame(game.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllBySubject(game), MESSAGE_ERROR);
    }



    @Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        when(repositoryMock.save(any(FavoriteEntity.class))).thenReturn(new FavoriteEntity());

        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }
    

    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.save(any(FavoriteEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new Favorite(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(new FavoriteEntity()));
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

    @Test
    void updateForceExceptionTest() {
        Favorite item = new Favorite();
        item.setId(1);
        item.setGame(game);

        FavoriteEntity dbItemMock = mock(FavoriteEntity.class);
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setGame(IGameEntityMapper.INSTANCE.toEntity(game));

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.update(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
    }


    @Test
    void deleteAdminTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new FavoriteEntity(1)));
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

}

