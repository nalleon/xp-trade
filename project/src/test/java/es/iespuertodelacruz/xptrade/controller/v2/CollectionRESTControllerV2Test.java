package es.iespuertodelacruz.xptrade.controller.v2;

import es.iespuertodelacruz.xptrade.controllers.v2.CollectionRESTControllerV2;
import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.CollectionService;
import es.iespuertodelacruz.xptrade.domain.service.GameService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.input.CollectionInputDTO;
import es.iespuertodelacruz.xptrade.dto.input.GameInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.ICollectionOutputDTOMapper;
import es.iespuertodelacruz.xptrade.model.entities.GenreEntity;
import es.iespuertodelacruz.xptrade.model.service.rest.CollectionEntityService;
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

public class CollectionRESTControllerV2Test extends MapperDTOHelper {
    @Mock
    CollectionService serviceMock;
    @Mock
    UserService serviceUserMock;
    @Mock
    GameService serviceGameMock;
    @Mock
    CollectionEntityService entityServiceMock;

    @InjectMocks
    CollectionService serviceMockException;


    @InjectMocks
    CollectionRESTControllerV2 controller;




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
        List<Collection> list = new ArrayList<>();
        list.add(new Collection(new Game(), new User()));
        list.add(new Collection(new Game(), new User()));
        list.add(new Collection(new Game(), new User()));
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
        List<Collection> list = new ArrayList<>();
        list.add(new Collection(new Game(), new User()));
        list.add(new Collection(new Game(), new User()));
        list.add(new Collection(new Game(), new User()));
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
        List<Collection> list = new ArrayList<>();
        list.add(new Collection(new Game(), new User()));
        list.add(new Collection(new Game(), new User()));
        list.add(new Collection(new Game(), new User()));
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
        when(serviceMock.findById(1)).thenReturn(new Collection(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneNotFoundTest() {
        when(serviceMock.findById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.getById(1).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void addTest() {
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Collection());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        Assertions.assertEquals(HttpStatus.CREATED, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addGameNullTest() {
        CollectionInputDTO aux = new CollectionInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void addUserNullTest() {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }



    @Test
    void addThrowsExceptionTest() {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.add(any(Game.class), any(User.class))).thenThrow(new RuntimeException());
        when(entityServiceMock.save(any(Collection.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        ResponseEntity<CustomApiResponse<?>> response = controller.add(aux);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteTest() {
        Collection aux = new Collection(gameDomain, userDomain);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Collection aux = new Collection(gameDomain, userDomain);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(anyInt())).thenReturn(new Collection());
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Collection());
        when(serviceMock.update(anyInt(), any(Game.class), any(User.class))).thenReturn(new Collection());


        ResponseEntity responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Collection());
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() {
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(null);
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateGameNullTest() {
        CollectionInputDTO aux = new CollectionInputDTO(new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceMock.findById(anyInt())).thenReturn(new Collection());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID, aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void updateUserNullTest() {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceMock.findById(anyInt())).thenReturn(new Collection());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateIdNullTest() {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceMock.findById(anyInt())).thenReturn(new Collection());
        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.update(anyInt(), any(Game.class), any(User.class))).thenReturn(null);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        CollectionInputDTO aux = new CollectionInputDTO(
                new GameInputDTO(TITLE, COVER_ART, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                        new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        when(serviceGameMock.add(anyString(), anyString(), anySet(), anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(any(Integer.class))).thenReturn(new Collection());
        when(serviceMock.update(anyInt(),any(Game.class), any(User.class))).thenThrow(new RuntimeException());

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


}
