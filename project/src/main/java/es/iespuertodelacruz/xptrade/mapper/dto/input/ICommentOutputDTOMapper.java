package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.dto.output.CommentOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserDTOMapper.class, IGameOutputDTOMapper.class})
public interface ICommentOutputDTOMapper {
    ICommentOutputDTOMapper INSTANCE = Mappers.getMapper(ICommentOutputDTOMapper.class);
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment toDomain(CommentOutputDTO dto);
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    CommentOutputDTO toDTO(Comment domain);
    List<Comment> toDomainList(List<CommentOutputDTO> dtos);
    List<CommentOutputDTO> toDTOList(List<Comment> domains);


}