package es.iespuertodelacruz.xptrade.shared.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class CustomUserDetails implements UserDetails {

    /**
     * Properties
     */
    private final String username;
    private final String role;

    /**
     * Full constructor of the class
     * @param username of the user
     * @param role of the user
     */
    public CustomUserDetails(String username, String role) {
        this.username = username;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() { return null; 	}

    @Override
    public String getUsername() {
        return username;
    }
}
