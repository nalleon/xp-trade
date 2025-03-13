package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;

public record FavoriteDTO(int id, GameDTO game, UserDTO user) {
}
