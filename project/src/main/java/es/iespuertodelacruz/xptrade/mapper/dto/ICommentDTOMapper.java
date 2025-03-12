package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.dto.CommentDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserDTOMapper.class, IGameDTOMapper.class})
public interface ICommentDTOMapper {
    ICommentDTOMapper INSTANCE = Mappers.getMapper(ICommentDTOMapper.class);
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment toDomain(CommentDTO dto);
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    CommentDTO toDTO(Comment domain);
    List<Comment> toDomainList(List<CommentDTO> dtos);
    List<CommentDTO> toDTOList(List<Comment> domains);


}