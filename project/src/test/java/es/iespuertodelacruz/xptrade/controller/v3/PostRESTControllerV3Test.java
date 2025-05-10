package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v2.PostRESTControllerV2;
import es.iespuertodelacruz.xptrade.controllers.v3.PostRESTController;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.GameService;
import es.iespuertodelacruz.xptrade.domain.service.PostService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.input.GameInputDTO;
import es.iespuertodelacruz.xptrade.dto.input.PostInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
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
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceMock.add(any(Game.class), any(User.class), anyString(), anyString())).thenReturn(new Post());
        when(serviceGameMock.add(anyString(), anyString(), anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());


        Assertions.assertEquals(HttpStatus.CREATED, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addGameNullTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void addUserNullTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }



    @Test
    void addThrowsExceptionTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(entityServiceMock.save(any(Post.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        ResponseEntity<CustomApiResponse<?>> response = controller.add(aux);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteTest() {
        Post aux = new Post(gameDomain, userDomain, CONTENT, PICTURE);

        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity<?> responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Post aux = new Post(gameDomain, userDomain, CONTENT, PICTURE);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity<?> responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(anyInt())).thenReturn(new Post());
        when(serviceMock.add(any(Game.class), any(User.class), anyString(), anyString())).thenReturn(new Post());
        when(serviceMock.update(anyInt(), any(Game.class), any(User.class), anyString(), anyString())).thenReturn(new Post());

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() {
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateGameNullTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceMock.findById(anyInt())).thenReturn(new Post());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID, aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void updateUserNullTest() {

        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceMock.findById(anyInt())).thenReturn(new Post());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateIdNullTest() {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceMock.findById(anyInt())).thenReturn(new Post());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.update(anyInt(),any(Game.class), any(User.class), anyString(), anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        PostInputDTO aux = new PostInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()), 
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, 
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                CONTENT, PICTURE);

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(any(Integer.class))).thenReturn(new Post());
        when(serviceMock.update(anyInt(),any(Game.class), any(User.class), anyString(), anyString())).thenThrow(new RuntimeException());

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
