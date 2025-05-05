package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.dto.input.CommentInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CommentOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPostOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserDTOMapper.class, IPostOutputDTOMapper.class})
public interface ICommentInputDTOMapper {
    ICommentInputDTOMapper INSTANCE = Mappers.getMapper(ICommentInputDTOMapper.class);
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment toDomain(CommentInputDTO dto);
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    CommentInputDTO toDTO(Comment domain);
    List<Comment> toDomainList(List<CommentInputDTO> dtos);
    List<CommentInputDTO> toDTOList(List<Comment> domains);


}