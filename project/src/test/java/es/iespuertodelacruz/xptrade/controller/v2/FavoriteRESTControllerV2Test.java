package es.iespuertodelacruz.xptrade.controller.v2;

import es.iespuertodelacruz.xptrade.controllers.v2.FavoriteRESTControllerV2;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.service.FavoriteService;
import es.iespuertodelacruz.xptrade.domain.service.GameService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.input.FavoriteInputDTO;
import es.iespuertodelacruz.xptrade.dto.input.GameInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.service.rest.FavoriteEntityService;
import es.iespuertodelacruz.xptrade.model.service.rest.GameEntityService;
import es.iespuertodelacruz.xptrade.model.service.rest.UserEntityService;
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
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

public class FavoriteRESTControllerV2Test extends MapperDTOHelper {
    @Mock
    FavoriteService serviceMock;
    @Mock
    UserService serviceUserMock;
    @Mock
    GameService serviceGameMock;
    @Mock
    FavoriteEntityService entityServiceMock;
    @Mock
    GameEntityService gameEntityServiceMock;
    @Mock
    UserEntityService userEntityServiceMock;

    @InjectMocks
    FavoriteService serviceMockException;

    @InjectMocks
    FavoriteRESTControllerV2 controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        serviceMock.setRepository(entityServiceMock);
        serviceGameMock.setRepository(gameEntityServiceMock);
        serviceUserMock.setRepository(userEntityServiceMock);

        controller.setService(serviceMock);
        controller.setGameService(serviceGameMock);
        controller.setUserService(serviceUserMock);
        serviceMockException.setRepository(entityServiceMock);
    }
    @Test
    void getAllTest() {
        List<Favorite> list = new ArrayList<>();
        list.add(new Favorite(new User(), new Game()));
        list.add(new Favorite(new User(), new Game()));
        list.add(new Favorite(new User(), new Game()));
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
        List<Favorite> list = new ArrayList<>();

        list.add(new Favorite(new User(), new Game()));
        list.add(new Favorite(new User(), new Game()));
        list.add(new Favorite(new User(), new Game()));
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
        List<Favorite> list = new ArrayList<>();
        list.add(new Favorite(new User(), new Game()));
        list.add(new Favorite(new User(), new Game()));
        list.add(new Favorite(new User(), new Game()));
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
    void checkIfExistsTest() {
        when(serviceMock.checkIfExists(any(User.class), any(Game.class))).thenReturn(new Favorite(1));
        Assertions.assertNotNull(controller.checkIfExists(NAME, TITLE), MESSAGE_ERROR);
    }


    @Test
    void checkIfExistsNotFoundTest() {
        when(serviceMock.checkIfExists(any(User.class), any(Game.class))).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.checkIfExists(NAME, TITLE).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Favorite(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }


    @Test
    void getOneNotFoundTest() {
        when(serviceMock.findById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.getById(1).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));


        Assertions.assertEquals(HttpStatus.CREATED, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addGameNullTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(new GameInputDTO(TITLE, COVER_ART, SLUG, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void addUserNullTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }



    @Test
    void addThrowsExceptionTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.add(any(Game.class), any(User.class))).thenThrow(new RuntimeException());
        when(entityServiceMock.save(any(Favorite.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        ResponseEntity<CustomApiResponse<?>> response = controller.add(aux);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteTest() {
        Favorite aux = new Favorite(userDomain, gameDomain);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Favorite aux = new Favorite(userDomain, gameDomain);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity<?> responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME),
                        VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(anyInt())).thenReturn(new Favorite());
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
        when(serviceMock.update(anyInt(), any(Game.class), any(User.class))).thenReturn(new Favorite());


        ResponseEntity responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
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
        FavoriteInputDTO aux = new FavoriteInputDTO(new GameInputDTO(TITLE, COVER_ART, SLUG, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceMock.findById(anyInt())).thenReturn(new Favorite());
        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID, aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void updateUserNullTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceMock.findById(anyInt())).thenReturn(new Favorite());
        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateIdNullTest() {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));
        when(serviceMock.findById(anyInt())).thenReturn(new Favorite());
        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.update(anyInt(), any(Game.class), any(User.class))).thenReturn(null);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        FavoriteInputDTO aux = new FavoriteInputDTO(
                new GameInputDTO(TITLE, COVER_ART, SLUG,new HashSet<>(), new HashSet<>(),
        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE));

        when(serviceGameMock.add(anyString(), anyString(), anyString(),  anySet(),
                anySet(), anySet(), anySet(), anySet())).thenReturn(new Game());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(any(Integer.class))).thenReturn(new Favorite());
        when(serviceMock.update(anyInt(),any(Game.class), any(User.class))).thenThrow(new RuntimeException());

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


}
