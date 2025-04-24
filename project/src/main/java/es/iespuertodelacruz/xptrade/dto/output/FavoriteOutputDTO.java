package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public record FavoriteOutputDTO(int id, GameOutputDTO game, UserDTO user) {
}
