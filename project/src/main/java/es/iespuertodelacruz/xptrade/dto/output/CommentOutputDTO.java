package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

import java.util.Date;

public record CommentOutputDTO(int id, PostOutputDTO post, UserDTO user, String content, Date creationDate) {
}
