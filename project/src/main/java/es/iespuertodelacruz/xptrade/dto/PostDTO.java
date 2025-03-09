package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;

import java.util.Date;

public record PostDTO (int id, GameDTO game, UserOutputDTO dto, String content, String picture, Date creationDate) {
}
