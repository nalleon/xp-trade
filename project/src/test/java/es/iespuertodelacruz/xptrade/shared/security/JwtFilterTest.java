package es.iespuertodelacruz.xptrade.shared.security;

import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import es.iespuertodelacruz.xptrade.shared.security.JwtFilter;
import es.iespuertodelacruz.xptrade.shared.security.CustomUserDetails;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.*;
import org.springframework.security.core.context.SecurityContextHolder;


import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtFilterTest extends TestUtilities {

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

        Assertions.assertEquals(200, response.getStatus());
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

        Assertions.assertEquals(403, response.getStatus());
        Assertions.assertEquals("Account not verified yet", response.getContentAsString());
        verify(filterChain, never()).doFilter(any(), any());
    }

    @Test
    void testInvalidTokenShouldReturnUnauthorized() throws Exception {
        request.setRequestURI("/api/protected");
        request.addHeader("Authorization", "Bearer invalid.jwt.token");

        when(jwtService.validateAndGetClaims("invalid.jwt.token")).thenThrow(new com.auth0.jwt.exceptions.JWTVerificationException("Invalid"));

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertEquals(401, response.getStatus());
        verify(filterChain, never()).doFilter(any(), any());
    }
    @Test
    void testHeaderPresentButNotBearerPrefix() throws Exception {
        request.setRequestURI("/api/protected");
        request.addHeader("Authorization", "Token abc.def.ghi");

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication(), MESSAGE_ERROR);
        verify(jwtService, never()).validateAndGetClaims(any());
        verify(filterChain, never()).doFilter(request, response);
    }

    @Test
    void testHeaderNotPresent() throws Exception {
        request.setRequestURI("/api/protected");

        jwtFilter.doFilterInternal(request, response, filterChain);

        Assertions.assertNull(SecurityContextHolder.getContext().getAuthentication(), MESSAGE_ERROR);
        verify(jwtService, never()).validateAndGetClaims(any());
        verify(filterChain, never()).doFilter(request, response);
    }

}

