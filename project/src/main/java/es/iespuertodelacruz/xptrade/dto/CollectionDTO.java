package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;

public record CollectionDTO(int id, GameDTO game, UserSearchDTO user) {
}
