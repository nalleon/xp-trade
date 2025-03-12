package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;

import java.util.Date;

public record PostDTO (int id, GameDTO game, UserSearchDTO user, String content, String picture, Date creationDate) {
}
