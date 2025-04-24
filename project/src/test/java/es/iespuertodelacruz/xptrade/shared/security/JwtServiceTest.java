package es.iespuertodelacruz.xptrade.shared.security;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JwtServiceTest extends TestUtilities {

    private JwtService jwtService;

    @BeforeEach
    void beforeEach() {
        jwtService = new JwtService();
    }

    @Test
    void generateTokenAndValidate_success() {
        String username = "testuser";
        String role = "ROLE_USER";
        int verified = 1;

        String token = jwtService.generateToken(username, role, verified);
        Map<String, String> claims = jwtService.validateAndGetClaims(token);

        Assertions.assertEquals(username, claims.get("username"), MESSAGE_ERROR);
        Assertions.assertEquals(role, claims.get("role"), MESSAGE_ERROR);
        Assertions.assertEquals(String.valueOf(verified), claims.get("verified"), MESSAGE_ERROR);
    }

    @Test
    void validateAndGetClaims_invalidToken_shouldThrow() {
        String invalidToken = "invalid.token.here";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            jwtService.validateAndGetClaims(invalidToken);
        });

        Assertions.assertNotNull(exception.getMessage(), MESSAGE_ERROR);
    }
}
