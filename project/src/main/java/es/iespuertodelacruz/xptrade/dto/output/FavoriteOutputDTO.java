package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

public record FavoriteOutputDTO(int id, GameOutputDTO game, UserDTO user) {
}
