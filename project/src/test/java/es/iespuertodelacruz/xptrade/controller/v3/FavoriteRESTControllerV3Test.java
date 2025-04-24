package es.iespuertodelacruz.xptrade.controller.v3;


import es.iespuertodelacruz.xptrade.controllers.v3.FavoriteRESTController;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.FavoriteService;
import es.iespuertodelacruz.xptrade.domain.service.GameService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.output.FavoriteOutputDTO;
import es.iespuertodelacruz.xptrade.model.service.rest.FavoriteEntityService;
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

public class FavoriteRESTControllerV3Test extends MapperDTOHelper {
    @Mock
    FavoriteService serviceMock;
    @Mock
    UserService serviceUserMock;
    @Mock
    GameService serviceGameMock;
    @Mock
    FavoriteEntityService entityServiceMock;

    @InjectMocks
    FavoriteService serviceMockException;

    @InjectMocks
    FavoriteRESTController controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
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
        FavoriteOutputDTO aux = new FavoriteOutputDTO(1, gameOutputDTO, userDTO);

        Assertions.assertEquals(HttpStatus.CREATED, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void addThrowsExceptionTest() {
        FavoriteOutputDTO dto = new FavoriteOutputDTO(1, gameOutputDTO, userDTO);

        when(entityServiceMock.save(any(Favorite.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        ResponseEntity<CustomApiResponse<?>> response = controller.add(dto);

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
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        Favorite aux = new Favorite(userDomain, gameDomain);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
        when(serviceMock.update(any(Integer.class), any(Game.class), any(User.class))).thenReturn(aux);

        ResponseEntity responseEntity = controller.update(1, new FavoriteOutputDTO(aux.getId(), gameOutputDTO, userDTO));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        Favorite aux = new Favorite(1);
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
        ResponseEntity responseEntity = controller.update(1, new FavoriteOutputDTO(aux.getId(), gameOutputDTO, userDTO));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateInvalidDataTest() {
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(null);
        ResponseEntity responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Favorite aux = new Favorite(1);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(null);

        when(serviceMock.update(1, gameDomain, userDomain)).thenThrow(new RuntimeException("Database error"));
        ResponseEntity responseEntity = controller.update(1, new FavoriteOutputDTO(aux.getId(), gameOutputDTO, userDTO));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
