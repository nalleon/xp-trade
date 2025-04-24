package es.iespuertodelacruz.xptrade.shared.security;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class CustomUserDetailsTest extends TestUtilities {
    private final String USERNAME = "testuser";
    private final String ROLE = "ROLE_USER";

    @Test
    void getUsername_ShouldReturnCorrectUsername() {
        CustomUserDetails userDetails = new CustomUserDetails(USERNAME, ROLE);
        Assertions.assertEquals(USERNAME, userDetails.getUsername(), MESSAGE_ERROR);
    }

    @Test
    void getPassword_ShouldReturnNull() {
        CustomUserDetails userDetails = new CustomUserDetails(USERNAME, ROLE);
        Assertions.assertNull(userDetails.getPassword(), MESSAGE_ERROR);
    }

    @Test
    void getAuthorities_ShouldReturnCorrectRole() {
        CustomUserDetails userDetails = new CustomUserDetails(USERNAME, ROLE);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        Assertions.assertEquals(1, authorities.size(), MESSAGE_ERROR);
        Assertions.assertTrue(authorities.contains(new SimpleGrantedAuthority(ROLE)), MESSAGE_ERROR);
    }

    @Test
    void getAuthorities_WithDifferentRole_ShouldWork() {
        String role = "ADMIN";
        CustomUserDetails userDetails = new CustomUserDetails(USERNAME, role);
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();

        Assertions.assertEquals(1, authorities.size(), MESSAGE_ERROR);
        Assertions.assertEquals("ADMIN", authorities.get(0).getAuthority(), MESSAGE_ERROR);
    }
}
