package es.iespuertodelacruz.xptrade.dto.user;
import java.io.Serializable;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public record UserRegisterDTO(
        String name,
        String email,
        String password
) implements Serializable {

}