package es.iespuertodelacruz.xptrade.shared.security;

import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import es.iespuertodelacruz.xptrade.shared.security.JwtFilter;
import es.iespuertodelacruz.xptrade.shared.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.*;


import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtFilterTest {

    @InjectMocks
    private JwtFilter jwtFilter;

    @Mock
    private JwtService jwtService;

    @Mock
    private FilterChain filterChain;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void testPermittedRoute() throws ServletException, IOException {
        request.setRequestURI("/swagger-ui.html");

        jwtFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testValidTokenAndVerifiedUser() throws Exception {
        request.setRequestURI("/api/protected");
        request.addHeader("Authorization", "Bearer valid.jwt.token");

        Map<String, String> mockClaims = Map.of(
                "username", "user1",
                "role", "ROLE_USER",
                "verified", "1"
        );

        when(jwtService.validateAndGetClaims("valid.jwt.token")).thenReturn(mockClaims);

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertEquals(200, response.getStatus()); // no se interrumpe
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testValidTokenButUserNotVerified() throws Exception {
        request.setRequestURI("/api/protected");
        request.addHeader("Authorization", "Bearer valid.jwt.token");

        Map<String, String> mockClaims = Map.of(
                "username", "user1",
                "role", "ROLE_USER",
                "verified", "0"
        );

        when(jwtService.validateAndGetClaims("valid.jwt.token")).thenReturn(mockClaims);

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertEquals(403, response.getStatus());
        assertEquals("Account not verified yet", response.getContentAsString());
        verify(filterChain, never()).doFilter(any(), any());
    }

    @Test
    void testInvalidTokenShouldReturnUnauthorized() throws Exception {
        request.setRequestURI("/api/protected");
        request.addHeader("Authorization", "Bearer invalid.jwt.token");

        when(jwtService.validateAndGetClaims("invalid.jwt.token")).thenThrow(new com.auth0.jwt.exceptions.JWTVerificationException("Invalid"));

        jwtFilter.doFilterInternal(request, response, filterChain);

        assertEquals(401, response.getStatus());
        verify(filterChain, never()).doFilter(any(), any());
    }
}

