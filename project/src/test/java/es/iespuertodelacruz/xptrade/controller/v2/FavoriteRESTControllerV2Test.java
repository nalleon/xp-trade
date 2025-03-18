package es.iespuertodelacruz.xptrade.controller.v2;

import es.iespuertodelacruz.xptrade.controllers.v2.FavoriteRESTControllerV2;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.FavoriteService;
import es.iespuertodelacruz.xptrade.dto.FavoriteDTO;
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

public class FavoriteRESTControllerV2Test extends MapperDTOHelper {
    @Mock
    FavoriteService serviceMock;

    @InjectMocks
    FavoriteRESTControllerV2 controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new FavoriteRESTControllerV2();
        controller.setService(serviceMock);
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
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Favorite(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
        FavoriteDTO aux = new FavoriteDTO(1, gameDTO, userDTO);

        ResponseEntity responseEntity = controller.add(aux);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
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

        ResponseEntity responseEntity = controller.update(1, new FavoriteDTO(aux.getId(), gameDTO, userDTO));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        Favorite aux = new Favorite(1);
        when(serviceMock.add(any(Game.class), any(User.class))).thenReturn(new Favorite());
        ResponseEntity responseEntity = controller.update(1, new FavoriteDTO(aux.getId(), gameDTO, userDTO));
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
        ResponseEntity responseEntity = controller.update(1, new FavoriteDTO(aux.getId(), gameDTO, userDTO));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
