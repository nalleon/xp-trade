package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.DeveloperRESTController;
import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.service.DeveloperService;
import es.iespuertodelacruz.xptrade.dto.output.DeveloperOutputDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeveloperRESTControllerV3Test extends TestUtilities {
    @Mock
    DeveloperService serviceMock;

    @InjectMocks
    DeveloperRESTController controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller = new DeveloperRESTController();
        controller.setService(serviceMock);
    }
    @Test
    void getAllTest() {
        List<Developer> list = new ArrayList<>();
        list.add(new Developer(1));
        list.add(new Developer(2));
        list.add(new Developer(3));
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Developer(1));
        List<Developer> list = new ArrayList<>();
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(serviceMock.add(any(String.class))).thenReturn(new Developer());
        DeveloperOutputDTO aux = new DeveloperOutputDTO(1, "ADMIN");
        ResponseEntity responseEntity = controller.add(aux);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteTest() {
        Developer aux = new Developer(1);
        aux.setName(NAME);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Developer aux = new Developer(1);
        aux.setName(NAME);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        Developer aux = new Developer(1);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.add(any(String.class))).thenReturn(new Developer());
        when(serviceMock.update(any(Integer.class), any(String.class))).thenReturn(aux);

        ResponseEntity responseEntity = controller.update(1, new DeveloperOutputDTO(aux.getId(), aux.getName()));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        Developer aux = new Developer(1);
        when(serviceMock.add(any(String.class))).thenReturn(new Developer());
        ResponseEntity responseEntity = controller.update(1, new DeveloperOutputDTO(aux.getId(), aux.getName()));
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
        Developer aux = new Developer(1);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.update(any(Integer.class), any(String.class))).thenReturn(null);

        when(serviceMock.update(1, aux.getName())).thenThrow(new RuntimeException("Database error"));
        ResponseEntity responseEntity = controller.update(1, new DeveloperOutputDTO(aux.getId(), aux.getName()));
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
