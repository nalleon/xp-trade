package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

public record PostInputDTO(GameOutputDTO game, UserDTO user, String content, String picture) {
}
