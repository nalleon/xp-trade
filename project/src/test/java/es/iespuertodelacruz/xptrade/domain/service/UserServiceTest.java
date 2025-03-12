package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IUserRepository;
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

public class UserServiceTest extends TestUtilities {
    @Mock
    IUserRepository repositoryMock;

    @InjectMocks
    UserService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new UserService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        list.add(new User(NAME, PASSWORD, EMAIL));
        list.add(new User("test2", PASSWORD, "test2@gmail.com"));
        list.add(new User("test3", PASSWORD, "test3@gmail.com"));
        Mockito.when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneTest() {
        Mockito.when(repositoryMock.findById(1)).thenReturn(new User());
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByNameTest() {
        Mockito.when(repositoryMock.findByUserame(NAME)).thenReturn(new User());
        Assertions.assertNotNull(service.findByUsername(NAME), MESSAGE_ERROR);
    }

    @Test
    void getByNameNullTest() {
        Assertions.assertNull(service.findByUsername(NAME), MESSAGE_ERROR);
    }

    @Test
    void getByEmailTest() {
        Mockito.when(repositoryMock.findByEmail(EMAIL)).thenReturn(new User());
        Assertions.assertNotNull(service.findByEmail(EMAIL), MESSAGE_ERROR);
    }

    @Test
    void getByEmailNullTest() {
        Assertions.assertNull(service.findByEmail(EMAIL), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        Mockito.when(repositoryMock.save(Mockito.any(User.class))).thenReturn(new User());
        Assertions.assertNotNull(service.add(NAME, PASSWORD, EMAIL), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.add(null, null, null), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        Mockito.when(repositoryMock.findById(1)).thenThrow(new RuntimeException("Database error"));
        Assertions.assertNull(service.update(NAME, EMAIL, PASSWORD), MESSAGE_ERROR);
    }
    @Test
    void updateTest() throws Exception {
        Mockito.when(repositoryMock.update(Mockito.any(User.class))).thenReturn(new User());
        Assertions.assertNotNull(service.update(NAME, EMAIL, PASSWORD), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() throws Exception {
        Assertions.assertNull(service.update(null, null, null), MESSAGE_ERROR);
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
