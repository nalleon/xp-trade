package es.iespuertodelacruz.xptrade.shared.security;



import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Service
public class AuthService {
    /**
     * Properties
     */
    private IUserService service;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    /**
     * Setters of the user service
     * @param service of the user
     */
    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }


    /**
     * Setters of the user service
     * @param jwtService of the role
     */
    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Setters of the user service
     * @param passwordEncoder of the role
     */
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Function to register a new user
     * @param username of the user
     * @param password of the user
     * @param email of the user
     * @return the user if everything OK, null otherwise
     */
    public User register(String username, String password, String email) {
        return service.add(username, email, passwordEncoder.encode(password));

    }

    /**
     * Function to authenticate a user
     * @param username of the user
     * @param password of the user
     * @return token if everything goes well, null otherwise
     */
    public String authenticate(String username, String password)  {
        String generateToken = null;
        User user = service.findByUsername(username);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                generateToken = jwtService.generateToken(user.getUsername(), user.getRole().getName(), user.getVerified());
            }
        }

        return generateToken;
    }
}

