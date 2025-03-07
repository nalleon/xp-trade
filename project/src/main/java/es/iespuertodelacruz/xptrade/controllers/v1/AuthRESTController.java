package es.iespuertodelacruz.xptrade.controllers.v1;

import es.iespuertodelacruz.xptrade.shared.config.MailService;
import es.iespuertodelacruz.xptrade.shared.security.AuthService;
import es.iespuertodelacruz.xptrade.shared.utils.ApiResponse;
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
@Tag(name="v1", description = "For authentication")
public class AuthRESTController {
    /**
     * Properties
     */
    @Autowired
    private IUserService service;
    @Autowired
    private MailService mailService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO loginDTO ) {
        String token = authService.authenticate(loginDTO.name(), loginDTO.password());

        if (token == null) {
            throw new RuntimeException("Invalid credentials");
        }

        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO registerDTO ) {

        authService.register(registerDTO.name(), registerDTO.password(), registerDTO.email());

        User user = service.findByEmail(registerDTO.email());
//        System.out.println(user);
        String authToken = user.getVerificationToken();


        String confirmationUrl =
                "http://localhost:8080/api/v1/auth/confirmation?email=" + registerDTO.email() + "&token=" + authToken;

        String[] senders = {registerDTO.email()};
        mailService.send(senders, "Verify your email " + user.getUsername(), confirmationUrl);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "En breves momentos, le llegara un correo de verificacion",
                        null));}

    @GetMapping("/confirmation")
    public ResponseEntity<?> confirmation (@RequestParam String email, @RequestParam String token){
        User authUser = service.findByEmail(email);
        if(authUser != null) {
            String tokenDB = authUser.getVerificationToken();

            if(tokenDB != null && tokenDB.equals(token)) {
                authUser.setVerified(1);
                User verified = service.updateVerify(authUser.getUsername(), authUser.getEmail(), authUser.getPassword(),
                        authUser.getVerified());

                return ResponseEntity.ok("Your account"+ verified.getUsername() + " is now verified.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
}
