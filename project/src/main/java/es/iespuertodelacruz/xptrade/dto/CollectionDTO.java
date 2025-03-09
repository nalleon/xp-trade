package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;

public record CollectionDTO(int id, GameDTO game, UserOutputDTO user) {
}
