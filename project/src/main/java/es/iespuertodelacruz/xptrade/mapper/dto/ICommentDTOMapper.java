package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.dto.CommentDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserSearchDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserSearchDTOMapper.class, IGameDTOMapper.class})
public interface ICommentDTOMapper {
    ICommentDTOMapper INSTANCE = Mappers.getMapper(ICommentDTOMapper.class);
    Comment toDomain(CommentDTO dto);
    CommentDTO toDTO(Comment domain);
    List<Comment> toDomainList(List<CommentDTO> dtos);
    List<CommentDTO> toDTOList(List<Comment> domains);


}