package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.UserRESTController;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.RoleService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserUpdateInputDTO;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.model.service.rest.UserEntityService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class UserRESTControllerV3Test extends TestUtilities {
    @Mock
    UserService serviceMock;
    @Mock
    PasswordEncoder passwordEncoderMock;
    @Mock
    FileStorageService storageServiceMock;

    @Mock
    UserEntityService restServiceMock;

    @Mock
    IUserEntityRepository repositoryMock;
    @Mock
    IRoleEntityRepository repositoryRoleMock;


    @InjectMocks
    UserRESTController controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        serviceMock = new UserService();
        storageServiceMock = new FileStorageService();

        restServiceMock = new UserEntityService();
        restServiceMock.setRoleRepository(repositoryRoleMock);
        restServiceMock.setRepository(repositoryMock);

        serviceMock.setRepository(restServiceMock);

        controller = new UserRESTController();
        controller.setService(serviceMock);
        controller.setPasswordEncoder(passwordEncoderMock);
        controller.setStorageService(storageServiceMock);
    }
    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new User());
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(passwordEncoderMock.encode(PASSWORD)).thenReturn("encoded");
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(ID);
        roleEntity.setName(NAME);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(ID);
        userEntity.setUsername(USERNAME);
        userEntity.setEmail(EMAIL);
        userEntity.setPassword(PASSWORD);
        userEntity.setCreationDate(CREATION_DATE);
        userEntity.setVerificationToken(VERIFICATION_TOKEN);
        userEntity.setProfilePicture(PROFILE_PICTURE);
        userEntity.setRole(roleEntity);
        userEntity.setVerified(VERIFIED);

        UserEntity result = userEntity;
        result.setId(ID);

        when(repositoryRoleMock.findRoleByName("ROLE_USER")).thenReturn(Optional.of(roleEntity));
        when(repositoryMock.save(any(UserEntity.class))).thenReturn(result);
        when(serviceMock.add(NAME, PASSWORD, EMAIL)).thenReturn(new User());


        ResponseEntity<?> responseEntity = controller.add(new UserRegisterDTO(NAME, EMAIL, PASSWORD));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addTestNullRequest() {
        ResponseEntity<?> responseEntity = controller.add(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), "Request should be rejected with BAD_REQUEST.");
        Assertions.assertTrue(responseEntity.getBody() instanceof CustomApiResponse, "Response should be CustomApiResponse.");
    }

    @Test
    void addTestNameConflict() {
        User existingUser = new User();
        //"name", "pass", "mail", new Role(1, "ROLE_USER"

        when(serviceMock.findByUsername("name")).thenReturn(existingUser);

        ResponseEntity<?> responseEntity = controller.add(new UserRegisterDTO(NAME, EMAIL, PASSWORD));

        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode(), "User creation should be rejected due to name conflict.");
        Assertions.assertTrue(responseEntity.getBody() instanceof CustomApiResponse, "Response should be CustomApiResponse.");
    }

    @Test
    void addTestEmailConflict() {
        User existingUser = new User();
        when(serviceMock.findByUsername(EMAIL)).thenReturn(existingUser); // El email ya está en uso

        ResponseEntity<?> responseEntity = controller.add(new UserRegisterDTO(NAME, EMAIL, PASSWORD));

        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode(), "User creation should be rejected due to email conflict.");
        Assertions.assertTrue(responseEntity.getBody() instanceof CustomApiResponse, "Response should be CustomApiResponse.");
    }
    @Test
    void deleteTest() {
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void updateTest() {
        when(serviceMock.add(NAME, EMAIL, PASSWORD)).thenReturn(new User());
        ResponseEntity responseEntity = controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        when(serviceMock.update(eq(NAME), EMAIL, PASSWORD))
                .thenThrow(new RuntimeException("Database error"));
        Assertions.assertThrows(RuntimeException.class,
                () -> controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD)), MESSAGE_ERROR);
    }
}
