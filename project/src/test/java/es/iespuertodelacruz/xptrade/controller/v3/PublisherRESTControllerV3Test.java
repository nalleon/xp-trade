package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.PublisherRESTController;
import es.iespuertodelacruz.xptrade.controllers.v3.PublisherRESTController;
import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.service.PublisherService;
import es.iespuertodelacruz.xptrade.domain.service.PublisherService;
import es.iespuertodelacruz.xptrade.dto.input.PublisherInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PublisherOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PublisherOutputDTO;
import es.iespuertodelacruz.xptrade.model.service.rest.PublisherEntityService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PublisherRESTControllerV3Test extends TestUtilities {
    @Mock
    PublisherService serviceMock;

    @InjectMocks
    PublisherRESTController controller;

    @Mock
    PublisherEntityService entityServiceMock;

    @InjectMocks
    PublisherService serviceMockException;

    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller.setService(serviceMock);
        serviceMockException.setRepository(entityServiceMock);
    }

    @Test
    void getAllTest() {
        when(serviceMock.findAll()).thenReturn(new ArrayList<>(List.of(new Publisher())));
        Assertions.assertEquals(HttpStatus.OK, controller.getAll().getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllEmptyTest() {
        when(serviceMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAll().getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Publisher(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }
    @Test
    void getOneNotFoundTest() {
        when(serviceMock.findById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.getById(1).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getOneByNameTest() {
        when(serviceMock.findByName(anyString())).thenReturn(new Publisher(1));
        Assertions.assertNotNull(controller.getByName("A"), MESSAGE_ERROR);
    }
    @Test
    void getOneByNameNotFoundTest() {
        when(serviceMock.findByName(anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.getByName("A").getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void addTest() {
        when(serviceMock.add(any(String.class))).thenReturn(new Publisher());
        PublisherInputDTO aux = new PublisherInputDTO("ADMIN");
        ResponseEntity responseEntity = controller.add(aux);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addThrowsExceptionTest() {
        PublisherInputDTO dto = new PublisherInputDTO("ADMIN");

        when(entityServiceMock.save(any(Publisher.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.add(dto).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteTest() {
        Publisher aux = new Publisher(1);
        aux.setName(NAME);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Publisher aux = new Publisher(1);
        aux.setName(NAME);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        Publisher aux = new Publisher(1);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(String.class))).thenReturn(new Publisher());
        when(serviceMock.update(any(Integer.class), any(String.class))).thenReturn(aux);


        ResponseEntity responseEntity = controller.update(1, new PublisherInputDTO("ADMIN"));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        Publisher aux = new Publisher(1);
        when(serviceMock.add(any(String.class))).thenReturn(new Publisher());
        ResponseEntity responseEntity = controller.update(1, new PublisherInputDTO("ADMIN"));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateInvalidDataTest() {
        when(serviceMock.add(any(String.class))).thenReturn(null);
        ResponseEntity responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Publisher aux = new Publisher(1);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.update(any(Integer.class), any(String.class))).thenReturn(null);

        when(serviceMock.update(anyInt(), anyString())).thenThrow(new RuntimeException("Database error"));
        ResponseEntity responseEntity = controller.update(1,new PublisherInputDTO("ADMIN"));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
