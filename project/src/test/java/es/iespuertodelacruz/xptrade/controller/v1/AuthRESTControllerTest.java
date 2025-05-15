package es.iespuertodelacruz.xptrade.controller.v1;

import es.iespuertodelacruz.xptrade.controllers.v1.AuthRESTController;
import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.user.UserLoginDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.mapper.entity.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.model.service.rest.UserEntityService;
import es.iespuertodelacruz.xptrade.shared.config.MailService;
import es.iespuertodelacruz.xptrade.shared.security.AuthService;
import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AuthRESTControllerTest extends TestUtilities {
    public static final RoleEntity ROLE_USER = new RoleEntity(1, "ROLE_USER");
    @Mock
    IUserEntityRepository repositoryMock;
    @Mock
    IRoleEntityRepository repositoryRoleMock;

    @Mock
    UserEntityService serviceEntityMock;

    @InjectMocks
    UserService serviceMock;

    @Mock
    JwtService jwtServiceMock;

    @Mock
    AuthService authServiceMock;

    @Mock
    MailService mailServiceMock;

    @Mock
    PasswordEncoder passwordEncoderMock;

    @InjectMocks
    AuthRESTController controller;

    @Mock
    IUserEntityMapper userEntityMapperMock;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        serviceEntityMock.setRepository(repositoryMock);
        serviceEntityMock.setRoleRepository(repositoryRoleMock);
        serviceMock.setRepository(serviceEntityMock);

        authServiceMock.setPasswordEncoder(passwordEncoderMock);
        authServiceMock.setJwtService(jwtServiceMock);
        authServiceMock.setService(serviceMock);

        controller.setService(serviceMock);
        controller.setMailService(mailServiceMock);
        controller.setAuthService(authServiceMock);
        controller.setJwtService(jwtServiceMock);
    }


    @Test
    void testRegisterSuccess() {
        UserRegisterDTO registerDTO = new UserRegisterDTO(USERNAME, EMAIL, "noPuedoMas124");

        User result = new User();
        result.setId(ID);
        result.setUsername(USERNAME);
        result.setEmail(EMAIL);
        result.setPassword("noPuedoMas124");
        result.setCreationDate(CREATION_DATE);
        result.setVerificationToken(VERIFICATION_TOKEN);
        result.setProfilePicture(PROFILE_PICTURE);
        result.setRole(new Role(ID, ROLE_NAME));
        result.setVerified(VERIFIED);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(ID);
        userEntity.setUsername(USERNAME);
        userEntity.setEmail(EMAIL);
        userEntity.setPassword("noPuedoMas124");
        userEntity.setRole(ROLE_USER);
        userEntity.setVerificationToken("AAAAAAAAAAa");
        userEntity.setVerified(0);
        userEntity.setProfilePicture(null);
        userEntity.setCreationDate(new Date());

        User resultUpdated = IUserEntityMapper.INSTANCE.toDomain(userEntity);

        doReturn(userEntity).when(userEntityMapperMock).toEntity(result);
        doReturn(resultUpdated).when(userEntityMapperMock).toDomain(userEntity);

        when(repositoryMock.findUserByName(NAME)).thenReturn(Optional.empty());
        when(repositoryRoleMock.findRoleByName("ROLE_USER")).thenReturn(Optional.of(new RoleEntity("ROLE_USER")));
        when(repositoryMock.save(userEntity)).thenReturn(userEntity);

        doReturn(resultUpdated).when(serviceEntityMock).save(result);

        when(authServiceMock.register(USERNAME, "noPuedoMas124", EMAIL)).thenReturn(resultUpdated);

        ResponseEntity<?> response = controller.register(registerDTO);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status code should be CREATED.");
    }

    @Test
    void testRegisterFailNull() {
        UserRegisterDTO registerDTO = new UserRegisterDTO(USERNAME, EMAIL, "noPuedoMas124");

        when(authServiceMock.register(USERNAME, "noPuedoMas124", EMAIL)).thenReturn(null);

        ResponseEntity<?> response = controller.register(registerDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), MESSAGE_ERROR);
    }



    @Test
    void testRegisterFailure() {
        String USERNAME = "existingUser";
        String PASSWORD = "existingPass";
        String EMAIL = "existing@example.com";
        UserRegisterDTO registerDTO = new UserRegisterDTO(USERNAME, PASSWORD, EMAIL);

        when(authServiceMock.register(USERNAME, PASSWORD, EMAIL)).thenReturn(null);

        ResponseEntity<?> response = controller.register(registerDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), MESSAGE_ERROR);
        Assertions.assertTrue(response.getBody() instanceof CustomApiResponse, MESSAGE_ERROR);
    }

    @Test
    void isValidPasswordTest() {
        String USERNAME = "existingUser";
        String PASSWORD = "existingPass";
        String EMAIL = "existing@example.com";
        UserRegisterDTO registerDTO = new UserRegisterDTO(USERNAME, PASSWORD, EMAIL);

        when(authServiceMock.register(USERNAME, PASSWORD, EMAIL)).thenReturn(null);

        ResponseEntity<?> response = controller.register(registerDTO);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), MESSAGE_ERROR);
        Assertions.assertTrue(response.getBody() instanceof CustomApiResponse, MESSAGE_ERROR);
    }

    @Test
    void testLoginSuccess() {
        String USERNAME = "testUser";
        String PASSWORD = "testPass1";
        UserLoginDTO loginDTO = new UserLoginDTO(USERNAME, PASSWORD);
        String token = "valid.jwt.token";
        UserEntity userEntityMock = mock(UserEntity.class);
        when(repositoryMock.findUserByName(USERNAME)).thenReturn(Optional.of(userEntityMock));

        User aux = new User();
        aux.setUsername(USERNAME);
        aux.setPassword(PASSWORD);
        aux.setVerified(1);
        aux.setRole(new Role(1, "ROLE_TEST"));
        when(serviceMock.findByUsername(USERNAME)).thenReturn(aux);
        when(passwordEncoderMock.matches(PASSWORD, aux.getPassword())).thenReturn(true);

        when(authServiceMock.authenticate(USERNAME, PASSWORD)).thenReturn(token);

        String result = controller.login(loginDTO);
        Assertions.assertEquals(token, result, MESSAGE_ERROR);

    }

    @Test
    void testLoginFailure() {
        String USERNAME = "testUser";
        String PASSWORD = "wrongPass";
        UserLoginDTO loginDTO = new UserLoginDTO(USERNAME, PASSWORD);

        when(authServiceMock.authenticate(USERNAME, PASSWORD)).thenReturn(null);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            controller.login(loginDTO);
        });

        Assertions.assertEquals("Invalid credentials", exception.getMessage(), MESSAGE_ERROR);
    }

    @Test
    void testConfirmationOK() {
        User result = new User();
        result.setId(ID);
        result.setUsername(USERNAME);
        result.setEmail(EMAIL);
        result.setPassword(PASSWORD);
        result.setCreationDate(CREATION_DATE);
        result.setVerificationToken(VERIFICATION_TOKEN);
        result.setProfilePicture(PROFILE_PICTURE);
        result.setRole(new Role(ID, ROLE_NAME));
        result.setVerified(VERIFIED);

        when(serviceMock.findByEmail(EMAIL)).thenReturn(result);

        ResponseEntity<?> response = controller.confirmation(EMAIL, VERIFICATION_TOKEN);
        Assertions.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void testConfirmationNotFound() {
        when(serviceMock.findByEmail(EMAIL)).thenReturn(null);
        ResponseEntity<?> response = controller.confirmation(EMAIL, VERIFICATION_TOKEN);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void testConfirmationInvalidToken() {
        when(serviceMock.findByEmail(EMAIL)).thenReturn(new User());

        ResponseEntity<?> response = controller.confirmation(EMAIL, VERIFICATION_TOKEN);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void testConfirmationValidDiffernetToken() throws NoSuchMethodException {
        User userMock = mock(User.class);
        when(serviceMock.findByEmail(EMAIL)).thenReturn(userMock);
        when(userMock.getVerificationToken()).thenReturn("exampleToken");

        ResponseEntity<?> response = controller.confirmation(EMAIL, VERIFICATION_TOKEN);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void testValidPassword() {
        Assertions.assertTrue(controller.isValidPassword("Password1"));
        Assertions.assertTrue(controller.isValidPassword("A1b2c3d4"));
    }

    @Test
    void testTooShortPassword() {
        Assertions.assertFalse(controller.isValidPassword("P1a"));
    }

    @Test
    void testNullPassword() {
        Assertions.assertFalse(controller.isValidPassword(null));
    }

    @Test
    void testNoUppercase() {
        Assertions.assertFalse(controller.isValidPassword("password1"));
    }

    @Test
    void testNoLowercase() {
        Assertions.assertFalse(controller.isValidPassword("PASSWORD1"));
    }

    @Test
    void testNoDigit() {
        Assertions.assertFalse(controller.isValidPassword("Password"));
    }

    @Test
    void testOnlyDigits() {
        Assertions.assertFalse(controller.isValidPassword("12345678"));
    }

    @Test
    void testOnlyLetters() {
        Assertions.assertFalse(controller.isValidPassword("Password"));
    }

    @Test
    void testExactlyEightCharactersValid() {
       Assertions.assertTrue(controller.isValidPassword("Abcdef1g"));
    }

}
