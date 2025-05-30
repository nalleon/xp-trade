package es.iespuertodelacruz.xptrade.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;

import java.util.Date;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public record UserDTO (@JsonIgnore int id,
                       String username,
                       @JsonIgnore String password,
                       @JsonIgnore String email,
                       @JsonIgnore RoleOutputDTO role,
                       @JsonIgnore int verified,
                       @JsonIgnore String verificationToken,
                       @JsonIgnore Date creationDate,
                       String profilePicture){
}
