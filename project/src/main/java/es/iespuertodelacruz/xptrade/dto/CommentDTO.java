package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;

import java.util.Date;

public record CommentDTO (int id, PostDTO post, UserOutputDTO dto, String content, Date creationDate) {
}
