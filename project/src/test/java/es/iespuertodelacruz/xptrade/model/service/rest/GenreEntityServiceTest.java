package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.model.entities.GenreEntity;
import es.iespuertodelacruz.xptrade.model.repository.IGenreEntityRepository;
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
import static org.mockito.Mockito.*;

public class GenreEntityServiceTest extends TestUtilities {
    @Mock
    IGenreEntityRepository repositoryMock;

    @InjectMocks
    GenreEntityService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new GenreEntityService();
        service.setRepository(repositoryMock);
    }
    @Test
    void getAllTest() {
        List<GenreEntity> list = new ArrayList<>();
        list.add(new GenreEntity());
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
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new GenreEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getByNameTest() {
        when(repositoryMock.findByName(NAME)).thenReturn(Optional.of(new GenreEntity()));
        Assertions.assertNotNull(service.findByName(NAME), MESSAGE_ERROR);
    }


    @Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);
        Genre role = new Genre();
        role.setId(1);
        role.setName(NAME);

        when(repositoryMock.save(any(GenreEntity.class))).thenReturn(new GenreEntity());

        Assertions.assertNotNull(service.save(role), MESSAGE_ERROR);
    }

    @Test
    void addDupeTest() {
        when(repositoryMock.findByName(NAME)).thenReturn(Optional.of(new GenreEntity()));
        Genre role = new Genre();
        role.setId(1);
        role.setName(NAME);
        Assertions.assertNull(service.save(role), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }



    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.save(any(GenreEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new Genre(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        Genre role = new Genre();
        role.setId(1);
        role.setName(NAME);
        when(repositoryMock.findByName(role.getName())).thenReturn(Optional.of(new GenreEntity()));
        Assertions.assertNotNull(service.update(role), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertNull(service.update(null), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() throws Exception {
        Genre role = new Genre();
        role.setId(1);
        role.setName(NAME);
        when(repositoryMock.findByName(role.getName())).thenReturn(Optional.empty());
        Assertions.assertNull(service.update(role), MESSAGE_ERROR);
    }

    @Test
    void updateForceExceptionTest() {
        Genre item = new Genre();
        item.setId(1);
        item.setName(NAME);

        GenreEntity dbItemMock = mock(GenreEntity.class);
        when(repositoryMock.findByName(item.getName())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setName(NAME);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.update(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
    }


    @Test
    void deleteAdminTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new GenreEntity("ROLE_ADMIN")));
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

