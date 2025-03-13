package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Comment;
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

public class CommentServiceTest extends TestUtilities {
    @Mock
    IGenericSocialRepository<Comment, Integer, User, Post> repositoryMock;

    @InjectMocks
    CommentService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new CommentService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment(1));
        list.add(new Comment(2));
        list.add(new Comment(3));
        Mockito.when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(new Comment());
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getAllByGameNullTest() {
        Mockito.when(repositoryMock.findAllBySubject(new Post())).thenReturn(null);
        Assertions.assertNull(service.findByPost(new Post()), MESSAGE_ERROR);
    }


    @Test
    void getAllByGameTest() {
        Mockito.when(repositoryMock.findAllBySubject(new Post())).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(service.findByPost(new Post()), MESSAGE_ERROR);
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
        Mockito.when(repositoryMock.save(Mockito.any(Comment.class))).thenReturn(new Comment());
        Assertions.assertNotNull(service.add(new Post(), new User(), "TEST"), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.add(null, null, null), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenThrow(new RuntimeException("Database error"));
        Assertions.assertNull(service.update(1, new Post(), new User(), "TEST"), MESSAGE_ERROR);
    }
    @Test
    void updateTest() throws Exception {
        Mockito.when(repositoryMock.update(Mockito.any(Comment.class))).thenReturn(new Comment());
        Assertions.assertNotNull(service.update(1,new Post(), new User(), "TEST"), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() throws Exception {
        Assertions.assertNull(service.update(0, null, null, null), MESSAGE_ERROR);
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
