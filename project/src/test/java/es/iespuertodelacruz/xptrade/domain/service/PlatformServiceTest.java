package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
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

public class PlatformServiceTest extends TestUtilities {
    @Mock
    IGenericRepository<Platform, Integer, String> repositoryMock;

    @InjectMocks
    PlatformService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new PlatformService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<Platform> list = new ArrayList<>();
        list.add(new Platform("Admin"));
        list.add(new Platform("User"));
        list.add(new Platform("Guest"));
        Mockito.when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(new Platform());
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void addTest() {
        Mockito.when(repositoryMock.save(Mockito.any(Platform.class))).thenReturn(new Platform());
        Assertions.assertNotNull(service.add("Admin"), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.add(null), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenThrow(new RuntimeException("Database error"));
        Assertions.assertNull(service.update(1, "Admin"), MESSAGE_ERROR);
    }
    @Test
    void updateTest() throws Exception {
        Mockito.when(repositoryMock.update(Mockito.any(Platform.class))).thenReturn(new Platform());
        Assertions.assertNotNull(service.update(1, "Admin"), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() throws Exception {
        Assertions.assertNull(service.update(1, null), MESSAGE_ERROR);
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
