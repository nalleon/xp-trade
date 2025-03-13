package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IRoleRepository;
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

public class RoleServiceTest extends TestUtilities {
    @Mock
    IRoleRepository repositoryMock;

    @InjectMocks
    RoleService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new RoleService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<Role> list = new ArrayList<>();
        list.add(new Role(1, "Admin"));
        list.add(new Role(2, "User"));
        list.add(new Role(3, "Guest"));
        Mockito.when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(new Role());
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByNameTest() {
        Mockito.when(repositoryMock.findByName(NAME)).thenReturn(new Role());
        Assertions.assertNotNull(service.findByName(NAME), MESSAGE_ERROR);
    }

    @Test
    void getByNameNullTest() {
        Assertions.assertNull(service.findByName(NAME), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        Mockito.when(repositoryMock.save(Mockito.any(Role.class))).thenReturn(new Role());
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
        Mockito.when(repositoryMock.update(Mockito.any(Role.class))).thenReturn(new Role());
        Assertions.assertNotNull(service.update(1,"Admin"), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() throws Exception {
        Assertions.assertNull(service.update(0, null), MESSAGE_ERROR);
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
