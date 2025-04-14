package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

public record CollectionOutputDTO(int id, GameOutputDTO game, UserDTO user) {
}
