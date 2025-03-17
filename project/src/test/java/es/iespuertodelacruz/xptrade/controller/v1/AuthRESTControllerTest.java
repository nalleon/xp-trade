package es.iespuertodelacruz.xptrade.controller.v1;

import es.iespuertodelacruz.xptrade.controllers.v1.AuthRESTController;
import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.user.UserLoginDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
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
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

public class AuthRESTControllerTest extends TestUtilities {
    @Mock
    IUserEntityRepository repositoryMock;
    @Mock
    IRoleEntityRepository repositoryRoleMock;

    @Mock
    UserEntityService serviceRestMock;

    @Mock
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


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        serviceRestMock.setRepository(repositoryMock);
        serviceRestMock.setRoleRepository(repositoryRoleMock);
        serviceMock.setRepository(serviceRestMock);
        controller = new AuthRESTController();
//        authServiceMock = new AuthService();
//        authServiceMock.setPasswordEncoder(passwordEncoderMock);
//        authServiceMock.setJwtService(jwtServiceMock);
//        authServiceMock.setService(serviceMock);
//
        controller.setService(serviceMock);
        controller.setMailService(mailServiceMock);
        authServiceMock.setService(serviceMock);
        controller.setAuthService(authServiceMock);
        controller.setJwtService(jwtServiceMock);
    }
    @Test
    void testLoginSuccess() {
        String USERNAME = "testUser";
        String PASSWORD = "testPass";
        UserLoginDTO loginDTO = new UserLoginDTO(USERNAME, PASSWORD);
        String token = "valid.jwt.token";

        when(authServiceMock.authenticate(USERNAME, PASSWORD)).thenReturn(token);

        String result = controller.login(loginDTO);

        Assertions.assertEquals(token, result, "The token should match the mocked value.");
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

        Assertions.assertEquals("Invalid credentials", exception.getMessage(), "The exception message should match.");
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
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void testConfirmationInvalidToken() {
        when(serviceMock.findByEmail(EMAIL)).thenReturn(new User());

        ResponseEntity<?> response = controller.confirmation(EMAIL, VERIFICATION_TOKEN);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), MESSAGE_ERROR);
    }

    //@Test
    void testRegisterSuccess() {
        UserRegisterDTO registerDTO = new UserRegisterDTO(USERNAME, PASSWORD, EMAIL);
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

        when(authServiceMock.register(USERNAME, PASSWORD, EMAIL)).thenReturn(result);
        when(serviceMock.add(USERNAME, PASSWORD, EMAIL)).thenReturn(result);

        ResponseEntity<?> response = controller.register(registerDTO);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Status code should be CREATED.");
        Assertions.assertTrue(response.getBody() instanceof CustomApiResponse, "Response body should be CustomApiResponse.");
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
}
