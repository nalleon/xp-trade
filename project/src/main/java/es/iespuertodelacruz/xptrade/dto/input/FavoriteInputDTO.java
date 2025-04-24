package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public record FavoriteInputDTO(int id, GameOutputDTO game, UserDTO user) {
}
