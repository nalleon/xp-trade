package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.dto.PostDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserSearchDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserSearchDTOMapper.class, IGameDTOMapper.class})
public interface IPostDTOMapper {
    IPostDTOMapper INSTANCE = Mappers.getMapper(IPostDTOMapper.class);
    Post toDomain(PostDTO dto);
    PostDTO toDTO(Post domain);
    List<Post> toDomainList(List<PostDTO> dtos);
    List<PostDTO> toDTOList(List<Post> domains);


}