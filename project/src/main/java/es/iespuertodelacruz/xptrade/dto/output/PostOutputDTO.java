package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

import java.util.Date;

public record PostOutputDTO(int id, GameOutputDTO game, UserDTO user, String content, String picture, Date creationDate) {
}
