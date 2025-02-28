package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.primary.dto;
import java.io.Serializable;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
public record UserRegisterDTO(
        String name,
        String email,
        String password
) implements Serializable {

}