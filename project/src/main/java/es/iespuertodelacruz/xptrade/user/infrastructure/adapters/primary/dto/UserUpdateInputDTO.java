package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.primary.dto;

import java.io.Serializable;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
public record UserUpdateInputDTO(
        String email,
        String password
) implements Serializable {

}