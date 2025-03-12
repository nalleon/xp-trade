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
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        return service.add(user.getUsername(), user.getEmail(), user.getPassword());

    }

    /**
     * Funcion para autenticar un User de cara al login
     * @param username del User
     * @param password del User
     * @return token si la autenticacion fue exitosa, null si algo fue mal
     */
    public String authenticate(String username, String password)  {
        String generateToken = null;
        User user = service.findByUsername(username);

        if (user != null) {
            System.out.println(password);
            if (passwordEncoder.matches(password, user.getPassword())) {
                generateToken = jwtService.generateToken(user.getUsername(), user.getRole().getName(), user.getVerified());
            }
        }

        System.out.println(generateToken);

        return generateToken;
    }
}

