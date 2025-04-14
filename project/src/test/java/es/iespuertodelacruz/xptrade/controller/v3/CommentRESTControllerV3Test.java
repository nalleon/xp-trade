package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.CommentRESTController;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.CommentService;
import es.iespuertodelacruz.xptrade.dto.output.CommentOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CommentRESTControllerV3Test extends MapperDTOHelper {
    @Mock
    CommentService serviceMock;

    @InjectMocks
    CommentRESTController controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new CommentRESTController();
        controller.setService(serviceMock);
    }
    @Test
    void getAllTest() {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment(1));
        list.add(new Comment(2));
        list.add(new Comment(3));
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Comment(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(serviceMock.add(any(Post.class), any(User.class), any(String.class))).thenReturn(new Comment());
        CommentOutputDTO aux = new CommentOutputDTO(1, postOutputDTO, userDTO, CONTENT, CREATION_DATE);

        ResponseEntity responseEntity = controller.add(aux);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteTest() {
        Comment aux = new Comment(postDomain, userDomain, CONTENT);

        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Comment aux = new Comment(postDomain, userDomain, CONTENT);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        Comment aux = new Comment(postDomain, userDomain, CONTENT);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(Post.class), any(User.class), any(String.class))).thenReturn(new Comment());
        when(serviceMock.update(any(Integer.class), any(Post.class), any(User.class),any(String.class))).thenReturn(aux);
        CommentOutputDTO result = new CommentOutputDTO(1, postOutputDTO, userDTO, CONTENT, CREATION_DATE);

        ResponseEntity responseEntity = controller.update(1, result);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        Comment aux = new Comment(1);
        CommentOutputDTO result = new CommentOutputDTO(1, postOutputDTO, userDTO, CONTENT, CREATION_DATE);

        when(serviceMock.add(any(Post.class), any(User.class), any(String.class))).thenReturn(new Comment());
        ResponseEntity responseEntity = controller.update(1, result);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateInvalidDataTest() {
        when(serviceMock.add(any(Post.class), any(User.class), any(String.class))).thenReturn(null);
        ResponseEntity responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Comment aux = new Comment(1);
        CommentOutputDTO result = new CommentOutputDTO(1, postOutputDTO, userDTO, CONTENT, CREATION_DATE);

        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(Post.class), any(User.class), any(String.class))).thenReturn(null);

        when(serviceMock.update(1, postDomain, userDomain, CONTENT)).thenThrow(new RuntimeException("Database error"));
        ResponseEntity responseEntity = controller.update(1, result);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
