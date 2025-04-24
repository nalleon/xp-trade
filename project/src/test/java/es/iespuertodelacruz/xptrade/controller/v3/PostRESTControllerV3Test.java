package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v2.PostRESTControllerV2;
import es.iespuertodelacruz.xptrade.controllers.v3.PostRESTController;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.GameService;
import es.iespuertodelacruz.xptrade.domain.service.PostService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.model.service.rest.PostEntityService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PostRESTControllerV3Test extends MapperDTOHelper {
    @Mock
    PostService serviceMock;
    @Mock
    UserService serviceUserMock;
    @Mock
    GameService serviceGameMock;
    @Mock
    PostEntityService entityServiceMock;

    @InjectMocks
    PostService serviceMockException;
    @InjectMocks
    PostRESTController controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        serviceMock.setRepository(entityServiceMock);
        controller.setService(serviceMock);
        controller.setGameService(serviceGameMock);
        controller.setUserService(serviceUserMock);
        serviceMockException.setRepository(entityServiceMock);
    }
    @Test
    void getAllTest() {
        List<Post> list = new ArrayList<>();
        list.add(new Post(1));
        list.add(new Post(2));
        list.add(new Post(3));
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }

    @Test
    void getAllEmptyTest() {
        when(serviceMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAll().getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void getAllByUserTest() {
        List<Post> list = new ArrayList<>();
        list.add(new Post(1));
        list.add(new Post(2));
        list.add(new Post(3));
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.findByUser(new User())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByUser("A"), MESSAGE_ERROR);
    }


    @Test
    void getAllByUserNoFilterTest() {
        when(serviceMock.findByUser(new User())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByUser("A"), MESSAGE_ERROR);
    }

    @Test
    void getAllByUserEmptyTest() {
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.findByUser(new User())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByUser("A").getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllByGameTest() {
        List<Post> list = new ArrayList<>();
        list.add(new Post(1));
        list.add(new Post(2));
        list.add(new Post(3));
        when(serviceGameMock.findByTitle(anyString())).thenReturn(new Game());
        when(serviceMock.findByGame(new Game())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByGame("A"), MESSAGE_ERROR);
    }


    @Test
    void getAllByGameNoFilterTest() {
        when(serviceMock.findByGame(new Game())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByGame("A"), MESSAGE_ERROR);
    }

    @Test
    void getAllByGameEmptyTest() {
        when(serviceGameMock.findByTitle(anyString())).thenReturn(new Game());
        when(serviceMock.findByGame(new Game())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByGame("A").getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Post(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneNotFoundTest() {
        when(serviceMock.findById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.getById(1).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(serviceMock.add(any(Game.class), any(User.class), any(String.class), any(String.class))).thenReturn(new Post());
        PostOutputDTO aux = new PostOutputDTO(1, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        ResponseEntity responseEntity = controller.add(aux);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addThrowsExceptionTest() {
        PostOutputDTO dto = new PostOutputDTO(1, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        when(entityServiceMock.save(any(Post.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        ResponseEntity<CustomApiResponse<?>> response = controller.add(dto);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), MESSAGE_ERROR);
    }



    @Test
    void deleteTest() {
        Post aux = new Post(gameDomain, userDomain, CONTENT, PICTURE);

        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Post aux = new Post(gameDomain, userDomain, CONTENT, PICTURE);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        Post aux = new Post(gameDomain, userDomain, CONTENT, PICTURE);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(Game.class), any(User.class), any(String.class), any(String.class))).thenReturn(new Post());
        when(serviceMock.update(any(Integer.class), any(Game.class), any(User.class),any(String.class), any(String.class))).thenReturn(aux);
        PostOutputDTO result = new PostOutputDTO(1, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        ResponseEntity responseEntity = controller.update(1, result);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        Post aux = new Post(1);
        PostOutputDTO result = new PostOutputDTO(1, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        when(serviceMock.add(any(Game.class), any(User.class), any(String.class), any(String.class))).thenReturn(new Post());
        ResponseEntity responseEntity = controller.update(1, result);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateInvalidDataTest() {
        when(serviceMock.add(any(Game.class), any(User.class), any(String.class), any(String.class))).thenReturn(null);
        ResponseEntity responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Post aux = new Post(1);
        PostOutputDTO result = new PostOutputDTO(1, gameOutputDTO, userDTO, CONTENT, PICTURE, CREATION_DATE);

        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(Game.class), any(User.class), any(String.class), any(String.class))).thenReturn(null);

        when(serviceMock.update(1, gameDomain, userDomain, CONTENT, PICTURE)).thenThrow(new RuntimeException("Database error"));
        ResponseEntity responseEntity = controller.update(1, result);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
