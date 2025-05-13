package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.CommentEntity;
import es.iespuertodelacruz.xptrade.model.entities.CommentEntity;
import es.iespuertodelacruz.xptrade.model.repository.ICommentEntityRepository;
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

public class CommentEntityServiceTest extends TestUtilities {
    @Mock
    ICommentEntityRepository repositoryMock;

    @InjectMocks
    CommentEntityService service;

    User user;

    Post post;

    Comment item;

    Game game;
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

        game = new Game(TITLE, COVER_ART, SLUG, developerSet, genreSet, platformSet, publisherSet, regionSet);
        game.setId(ID);
        user = new User(ID);
        user.setUsername(NAME);

        post = new Post();
        post.setId(ID);
        post.setUser(user);
        post.setGame(game);
        
        item = new Comment(ID);
        item.setUser(user);
        item.setContent(CONTENT);
        item.setCreationDate(CREATION_DATE);
        item.setPost(post);

        MockitoAnnotations.openMocks(this);
        service = new CommentEntityService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<CommentEntity> list = new ArrayList<>();
        list.add(new CommentEntity());
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
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new CommentEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getByNameTest() {
        List<CommentEntity> list = new ArrayList<>();
        list.add(new CommentEntity());
        when(repositoryMock.findAllByUser(user.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByUser(user), MESSAGE_ERROR);
    }

    @Test
    void getByGameTest() {
        List<CommentEntity> list = new ArrayList<>();
        list.add(new CommentEntity());
        when(repositoryMock.findAllByPost(post.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllBySubject(post), MESSAGE_ERROR);
    }



    @Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        when(repositoryMock.save(any(CommentEntity.class))).thenReturn(new CommentEntity());

        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }




    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.save(any(CommentEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new Comment(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        when(repositoryMock.findById(post.getId())).thenReturn(Optional.of(new CommentEntity()));
        Assertions.assertNotNull(service.update(item), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertNull(service.update(null), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() throws Exception {
        when(repositoryMock.findById(post.getId())).thenReturn(Optional.empty());
        Assertions.assertNull(service.update(item), MESSAGE_ERROR);
    }

    @Test
    void updateForceExceptionTest() {
        Comment item = new Comment();
        item.setId(1);
        item.setContent(NAME);

        CommentEntity dbItemMock = mock(CommentEntity.class);
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setContent(NAME);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.update(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
    }


    @Test
    void deleteAdminTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new CommentEntity(1)));
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

