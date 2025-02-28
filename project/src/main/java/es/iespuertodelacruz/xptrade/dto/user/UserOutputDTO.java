package es.iespuertodelacruz.xptrade.dto.user;

import java.io.Serializable;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
public record UserOutputDTO(
        String nombre,
        String correo
) implements Serializable {

}