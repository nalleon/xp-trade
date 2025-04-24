package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

import java.util.Date;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public record CommentOutputDTO(int id, PostOutputDTO post, UserDTO user, String content, Date creationDate) {
}
