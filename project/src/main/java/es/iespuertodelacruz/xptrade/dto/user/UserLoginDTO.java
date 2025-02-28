package es.iespuertodelacruz.xptrade.dto.user;

import java.io.Serializable;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
public record UserLoginDTO(
        String name,
        String password
) implements Serializable {

}
