package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
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
public interface IPostOutputDTOMapper {
    IPostOutputDTOMapper INSTANCE = Mappers.getMapper(IPostOutputDTOMapper.class);
    @Mapping(target = "user", source = "user")
    @Mapping(target = "game", source = "game")
    Post toDomain(PostOutputDTO dto);
    @Mapping(target = "game", source = "game")
    @Mapping(target = "user", source = "user")
    PostOutputDTO toDTO(Post domain);
    List<Post> toDomainList(List<PostOutputDTO> dtos);
    List<PostOutputDTO> toDTOList(List<Post> domains);


}