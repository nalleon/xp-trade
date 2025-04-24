package es.iespuertodelacruz.xptrade.shared.security;

import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest extends TestUtilities {

    @InjectMocks
    private AuthService authService;

    @Mock
    private IUserService userServiceMock;
    @Mock
    private JwtService jwtServiceMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService.setService(userServiceMock);
        authService.setJwtService(jwtServiceMock);
        authService.setPasswordEncoder(passwordEncoderMock);
    }

    @Test
    void registerSuccess() {
        String username = "user";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        String email = "user@example.com";

        User user = new User();
        when(passwordEncoderMock.encode(rawPassword)).thenReturn(encodedPassword);
        when(userServiceMock.add(username, email, encodedPassword)).thenReturn(user);

        User result = authService.register(username, rawPassword, email);

        Assertions.assertEquals(user, result, MESSAGE_ERROR);
        verify(passwordEncoderMock).encode(rawPassword);
        verify(userServiceMock).add(username, email, encodedPassword);
    }

    @Test
    void authenticateSuccess() {
        String username = "user";
        String rawPassword = "password";
        String hashedPassword = "hashedPassword";
        String role = "ROLE_USER";
        String expectedToken = "jwtToken";

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setVerified(1);
        user.setRole(new es.iespuertodelacruz.xptrade.domain.Role(1, role));

        when(userServiceMock.findByUsername(username)).thenReturn(user);
        when(passwordEncoderMock.matches(rawPassword, hashedPassword)).thenReturn(true);
        when(jwtServiceMock.generateToken(username, role, 1)).thenReturn(expectedToken);

        String token = authService.authenticate(username, rawPassword);

        Assertions.assertEquals(expectedToken, token, MESSAGE_ERROR);
    }

    @Test
    void authenticateFailsUserNotFound() {
        when(userServiceMock.findByUsername("user")).thenReturn(null);

        String token = authService.authenticate("user", "password");

        Assertions.assertNull(token, MESSAGE_ERROR);
    }

    @Test
    void authenticateFailsWrongPassword() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("hashed");
        user.setVerified(1);
        user.setRole(new es.iespuertodelacruz.xptrade.domain.Role(1, "ROLE_USER"));

        when(userServiceMock.findByUsername("user")).thenReturn(user);
        when(passwordEncoderMock.matches("wrongPassword", "hashed")).thenReturn(false);

        String token = authService.authenticate("user", "wrongPassword");

        Assertions.assertNull(token, MESSAGE_ERROR);
    }

    @Test
    void authenticateFailsUserNotVerified() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("hashed");
        user.setVerified(0);
        user.setRole(new es.iespuertodelacruz.xptrade.domain.Role(1, "ROLE_USER"));

        when(userServiceMock.findByUsername("user")).thenReturn(user);
        when(passwordEncoderMock.matches("password", "hashed")).thenReturn(true);

        String token = authService.authenticate("user", "password");

        Assertions.assertNull(token, MESSAGE_ERROR);
    }
}
