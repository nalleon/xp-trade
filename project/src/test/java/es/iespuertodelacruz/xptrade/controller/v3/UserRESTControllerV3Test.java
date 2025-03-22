package es.iespuertodelacruz.xptrade.controller.v3;

import es.iespuertodelacruz.xptrade.controllers.v3.UserRESTController;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.RoleService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserUpdateInputDTO;
import es.iespuertodelacruz.xptrade.mapper.entity.IUserEntityMapper;
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
import org.mockito.stubbing.OngoingStubbing;
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
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new UserEntity()));
        //when(serviceMock.findById(1)).thenReturn(new User());
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


        ResponseEntity<?> responseEntity = controller.add(new UserRegisterDTO(NAME, EMAIL, PASSWORD));
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addTestNullRequest() {
        ResponseEntity<?> responseEntity = controller.add(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), "Request should be rejected with BAD_REQUEST.");
        Assertions.assertInstanceOf(CustomApiResponse.class, responseEntity.getBody(), "Response should be CustomApiResponse.");
    }

   @Test
    void addTestNameConflict() {
        User existingUser = new User();
        existingUser.setUsername(NAME);
        //"name", "pass", "mail", new Role(1, "ROLE_USER"

        when(repositoryMock.findUserByName(NAME)).thenReturn(Optional.of(IUserEntityMapper.INSTANCE.toEntity(existingUser)));

        ResponseEntity<?> responseEntity = controller.add(new UserRegisterDTO(NAME, EMAIL, PASSWORD));

        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode(), "User creation should be rejected due to name conflict.");
        Assertions.assertInstanceOf(CustomApiResponse.class, responseEntity.getBody(), "Response should be CustomApiResponse.");
    }

    @Test
    void addTestEmailConflict() {
        User existingUser = new User();
        existingUser.setEmail(EMAIL);
        when(repositoryMock.findUserByEmail(EMAIL)).thenReturn(Optional.of(IUserEntityMapper.INSTANCE.toEntity(existingUser)));

        ResponseEntity<?> responseEntity = controller.add(new UserRegisterDTO(NAME, EMAIL, PASSWORD));

        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode(), "User creation should be rejected due to email conflict.");
        Assertions.assertInstanceOf(CustomApiResponse.class, responseEntity.getBody(), "Response should be CustomApiResponse.");
    }

    @Test
    void deleteOKTest() {
        when(repositoryMock.deleteEntityById(2)).thenReturn(1);

//        when(serviceMock.delete(2)).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(2);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void deleteAdminTest() {
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteFailTest() {
        ResponseEntity responseEntity = controller.delete(3);
        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() {
        ResponseEntity responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


    //@Test
    void updateTest() {
        User existingUser = new User(ID);
        existingUser.setEmail(EMAIL);
        existingUser.setUsername(USERNAME);
        when(repositoryMock.findUserByName(NAME)).thenReturn(Optional.of(IUserEntityMapper.INSTANCE.toEntity(existingUser)));
        when(serviceMock.update(NAME, EMAIL, PASSWORD)).thenReturn(new User());
        ResponseEntity responseEntity = controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    //@Test
    void updateExceptionTest() throws Exception {
        when(serviceMock.update(eq(NAME), EMAIL, PASSWORD))
                .thenThrow(new RuntimeException("Database error"));
        Assertions.assertThrows(RuntimeException.class,
                () -> controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD)), MESSAGE_ERROR);
    }
}