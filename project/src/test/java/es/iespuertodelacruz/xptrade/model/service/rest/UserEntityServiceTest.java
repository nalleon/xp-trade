package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.mapper.entity.IRoleEntityMapper;
import es.iespuertodelacruz.xptrade.mapper.entity.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
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

public class UserEntityServiceTest extends TestUtilities {
    @Mock
    IUserEntityRepository repositoryMock;
    @Mock
    IRoleEntityRepository repositoryRoleMock;

    @InjectMocks
    UserEntityService service;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        service = new UserEntityService();
        service.setRepository(repositoryMock);
        service.setRoleRepository(repositoryRoleMock);
    }
    @Test
    void getAllTest() {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity());
        when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByIdNameNullTest() {
        Assertions.assertNull(service.findByUserame(NAME), MESSAGE_ERROR);
    }

    @Test
    void getByEmailNullTest() {
        Assertions.assertNull(service.findByEmail(EMAIL), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new UserEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }


    @Test
    void getByNameTest() {
        when(repositoryMock.findUserByName(NAME)).thenReturn(Optional.of(new UserEntity()));
        Assertions.assertNotNull(service.findByUserame(NAME), MESSAGE_ERROR);
    }


    @Test
    void getByEmailTest() {
        when(repositoryMock.findUserByEmail(EMAIL)).thenReturn(Optional.of(new UserEntity()));
        Assertions.assertNotNull(service.findByEmail(EMAIL), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);
        User user = new User();
        user.setId(1);
        user.setRole(new Role(1, "ROLE_ADMIN"));
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername("nameTest");

        when(service.getRoleUser()).thenReturn(new RoleEntity());
        when(repositoryMock.save(any(UserEntity.class))).thenReturn(new UserEntity());

        Assertions.assertNotNull(service.save(user), MESSAGE_ERROR);
    }

    @Test
    void addDupeTest() {
        when(repositoryMock.findUserByName(NAME)).thenReturn(Optional.of(new UserEntity()));

        when(service.getRoleUser()).thenReturn(new RoleEntity());
        Assertions.assertNull(service.save(new User(NAME, EMAIL, PASSWORD)), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }

    @Test
    void getRoleUserTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setRole(new Role(1, "ROLE_ADMIN"));
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername("nameTest");
        when(service.getRoleUser()).thenReturn(new RoleEntity());

        when(repositoryRoleMock.findRoleByName(anyString())).thenReturn(Optional.of(new RoleEntity()));
        when(repositoryMock.save(any(UserEntity.class))).thenReturn(new UserEntity());
        Assertions.assertNotNull(service.save(user), MESSAGE_ERROR);
    }


    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.findUserByName(NAME)).thenReturn(null);

        when(repositoryRoleMock.findRoleByName("ROLE_USER")).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new User(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setRole(new Role(1, "ROLE_ADMIN"));
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername("nameTest");
        when(repositoryMock.findUserByName(user.getUsername())).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertNotNull(service.update(user), MESSAGE_ERROR);
    }

    @Test
    void updateVerifyTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setRole(new Role(1, "ROLE_ADMIN"));
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername("nameTest");
        user.setVerified(1);
        when(repositoryMock.findUserByName(user.getUsername())).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertNotNull(service.update(user), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertNull(service.update(null), MESSAGE_ERROR);
    }

    @Test
    void updateForceExceptionTest() {
        User item = new User();
        item.setId(1);
        item.setPassword(NAME);
        item.setUsername(USERNAME);
        item.setEmail(EMAIL);
        UserEntity dbItemMock = mock(UserEntity.class);
        when(repositoryMock.findUserByName(item.getUsername())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setPassword(NAME);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.update(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
    }

    @Test
    void deleteAdminTest() {
        UserEntity user = new UserEntity();
        user.setId(1);
        RoleEntity roleEntity = new RoleEntity(1);
        roleEntity.setName("ROLE_ADMIN");
        user.setRole(roleEntity);
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername("nameTest");
        when(repositoryMock.findById(1)).thenReturn(Optional.of(user));
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

    @Test
    void deleteNotEqualsTest() {
        RoleEntity roleAux = new RoleEntity();
        roleAux.setId(ID);
        roleAux.setName(NAME);

        UserEntity aux = new UserEntity(ID);
        aux.setRole(roleAux);
        when(repositoryMock.deleteEntityById(1)).thenReturn(0);
        when(repositoryMock.findById(1)).thenReturn(Optional.of(aux));
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void updateProfilePictureTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setRole(new Role(1, "ROLE_ADMIN"));
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername("nameTest");
        user.setProfilePicture(PICTURE);
        when(repositoryMock.findUserByName(user.getUsername())).thenReturn(Optional.of(new UserEntity()));

        Assertions.assertNotNull(service.updatePicture(user), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundPictureTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setRole(new Role(1, "ROLE_ADMIN"));
        user.setEmail("example@email.com");
        user.setPassword("1q2w3e4r");
        user.setUsername(NAME);
        user.setProfilePicture(PICTURE);

        when(repositoryMock.findUserByName(any())).thenReturn(Optional.empty());

        Assertions.assertNull(service.updatePicture(user), MESSAGE_ERROR);
    }

    @Test
    void updatePictureFalseTest() throws Exception {
        Assertions.assertNull(service.updatePicture(null), MESSAGE_ERROR);
    }

    @Test
    void updatePictureForceExceptionTest() {
        User item = new User();
        item.setId(1);
        item.setPassword(NAME);
        item.setUsername(USERNAME);
        item.setEmail(EMAIL);
        item.setProfilePicture(PROFILE_PICTURE);
        UserEntity dbItemMock = mock(UserEntity.class);
        when(repositoryMock.findUserByName(item.getUsername())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setProfilePicture(PROFILE_PICTURE);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.updatePicture(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
    }
}
