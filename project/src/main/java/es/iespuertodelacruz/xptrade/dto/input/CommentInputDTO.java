package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;

import java.util.Date;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public record CommentInputDTO(int id, PostOutputDTO post, UserDTO user, String content) {
}
