package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.model.entities.PublisherEntity;
import es.iespuertodelacruz.xptrade.model.repository.IPublisherEntityRepository;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PublisherEntityServiceTest extends TestUtilities {
    @Mock
    IPublisherEntityRepository repositoryMock;

    @InjectMocks
    PublisherEntityService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new PublisherEntityService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<PublisherEntity> list = new ArrayList<>();
        list.add(new PublisherEntity());
        when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByIdNameNullTest() {
        Assertions.assertNull(service.findByName(NAME), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new PublisherEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getByNameTest() {
        when(repositoryMock.findByName(NAME)).thenReturn(Optional.of(new PublisherEntity()));
        Assertions.assertNotNull(service.findByName(NAME), MESSAGE_ERROR);
    }


    @Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);
        Publisher role = new Publisher();
        role.setId(1);
        role.setName(NAME);

        when(repositoryMock.save(any(PublisherEntity.class))).thenReturn(new PublisherEntity());

        Assertions.assertNotNull(service.save(role), MESSAGE_ERROR);
    }

    @Test
    void addDupeTest() {
        when(repositoryMock.findByName(NAME)).thenReturn(Optional.of(new PublisherEntity()));
        Publisher role = new Publisher();
        role.setId(1);
        role.setName(NAME);
        Assertions.assertNull(service.save(role), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }

//    @Test
//    void updateExceptionTest() throws Exception {
//        when(repositoryMock.findUserByName(NAME)).thenThrow(new RuntimeException());
//        Assertions.assertThrows(RuntimeException.class, () -> service.update(new User(1)), MESSAGE_ERROR);
//    }


    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.save(any(PublisherEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new Publisher(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        Publisher role = new Publisher();
        role.setId(1);
        role.setName(NAME);
        when(repositoryMock.findByName(role.getName())).thenReturn(Optional.of(new PublisherEntity()));
        Assertions.assertNotNull(service.update(role), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertNull(service.update(null), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() throws Exception {
        Publisher role = new Publisher();
        role.setId(1);
        role.setName(NAME);
        when(repositoryMock.findByName(role.getName())).thenReturn(Optional.empty());
        Assertions.assertNull(service.update(role), MESSAGE_ERROR);
    }

//    @Test
//    void updateExceptionTest() throws Exception {
//        Publisher role = new Publisher();
//        role.setId(1);
//        role.setName(NAME);
//        when(repositoryMock.findByName(role.getName())).thenReturn(Optional.empty());
//        Assertions.assertNull(service.update(role), MESSAGE_ERROR);
//    }

    @Test
    void deleteAdminTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new PublisherEntity("ROLE_ADMIN")));
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteNotFoundTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.empty());
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteTest() {
        when(repositoryMock.deleteEntityById(2)).thenReturn(2);
        Assertions.assertTrue(service.delete(2), MESSAGE_ERROR);
    }


    @Test
    void deleteNonExistentTest() {
        when(repositoryMock.deleteEntityById(1)).thenReturn(0);
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

}

