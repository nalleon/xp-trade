package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class PostServiceTest extends TestUtilities {
    @Mock
    IGenericSocialRepository<Post, Integer, User, Game> repositoryMock;

    @InjectMocks
    PostService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new PostService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<Post> list = new ArrayList<>();
        list.add(new Post(new Game(1), new User(1), "EEE", "default.png"));
        list.add(new Post(new Game(2), new User(2), "EEE", "default.png"));
        Mockito.when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(new Post());
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getAllByGameNullTest() {
        Mockito.when(repositoryMock.findAllBySubject(new Game())).thenReturn(null);
        Assertions.assertNull(service.findByGame(new Game()), MESSAGE_ERROR);
    }


    @Test
    void getAllByGameTest() {
        Mockito.when(repositoryMock.findAllBySubject(new Game())).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(service.findByGame(new Game()), MESSAGE_ERROR);
    }

    @Test
    void getAllByUserNullTest() {
        Mockito.when(repositoryMock.findAllByUser(new User())).thenReturn(null);
        Assertions.assertNull(service.findByUser(new User()), MESSAGE_ERROR);
    }


    @Test
    void getAllByUserTest() {
        Mockito.when(repositoryMock.findAllByUser(new User())).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(service.findByUser(new User()), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        Mockito.when(repositoryMock.save(Mockito.any(Post.class))).thenReturn(new Post());
        Assertions.assertNotNull(service.add(new Game(1), new User(1), "EEE", "default.png"), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.add(null, null, null, null), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenThrow(new RuntimeException("Database error"));
        Assertions.assertNull(service.update(1, new Game(1), new User(1), "EEE", "default.png"), MESSAGE_ERROR);
    }
    @Test
    void updateTest() throws Exception {
        Mockito.when(repositoryMock.update(Mockito.any(Post.class))).thenReturn(new Post());
        Assertions.assertNotNull(service.update(1,new Game(1), new User(1), "EEE", "default.png"), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() throws Exception {
        Assertions.assertNull(service.update(0, null, null, null, null), MESSAGE_ERROR);
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
