package es.iespuertodelacruz.xptrade.controllers.v1;

import es.iespuertodelacruz.xptrade.shared.config.MailService;
import es.iespuertodelacruz.xptrade.shared.security.AuthService;
import es.iespuertodelacruz.xptrade.shared.security.JwtService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.user.UserLoginDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
@Tag(name="v1 - Authentication", description = "For authentication")
public class AuthRESTController {
    /**
     * Properties
     */
    private IUserService service;

    private AuthService authService;
    private JwtService jwtService;

    private MailService mailService;

    /**
     * Setter of the user service
     * @param service of the user
     */
    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }

    /**
     * Setter of the mail service
     * @param mailService for the user
     */
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * Setter of the jwt service
     * @param jwtService for the user
     */
    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    /**
     * Setter of the jwt service
     * @param authService for the user
     */
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO loginDTO ) {
        String token = authService.authenticate(loginDTO.name().trim(), loginDTO.password());

        if (token == null) {
            throw new RuntimeException("Invalid credentials");
        }

        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO registerDTO ) {

        if (!isValidPassword(registerDTO.password())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomApiResponse<>(400, "Password not valid", null));
        }

        User user =  authService.register(registerDTO.name().trim(), registerDTO.password(), registerDTO.email());

        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomApiResponse<>(400, "Something went wrong",
                            null));
        }
        String authToken = user.getVerificationToken();

        String confirmationUrl =
                "http://localhost:8080/api/v1/auth/confirmation?email=" + registerDTO.email() + "&token=" + authToken;

        String[] senders = {registerDTO.email()};
        mailService.send(senders, "Verify your email " + user.getUsername(), confirmationUrl);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomApiResponse<>(201, "En breves momentos, le llegara un email de verificacion",
                        null));
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        for (char passwordCharacter : password.toCharArray()) {
            if (Character.isUpperCase(passwordCharacter)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(passwordCharacter)) {
                hasLowercase = true;
            } else if (Character.isDigit(passwordCharacter)) {
                hasDigit = true;
            }
        }

        return hasUppercase && hasLowercase && hasDigit;
    }

    @GetMapping("/confirmation")
    public ResponseEntity<?> confirmation (@RequestParam String email, @RequestParam String token){
        User authUser = service.findByEmail(email);
        if(authUser != null) {
            String tokenDB = authUser.getVerificationToken();

            if(tokenDB != null && tokenDB.equals(token)) {
                authUser.setVerified(1);
                User verified = service.updateVerify(authUser.getUsername(), authUser.getEmail(), authUser.getPassword(),
                        authUser.getVerified());

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Your account "+ authUser.getUsername() + " is now verified.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User not found.");
        }
    }
}
