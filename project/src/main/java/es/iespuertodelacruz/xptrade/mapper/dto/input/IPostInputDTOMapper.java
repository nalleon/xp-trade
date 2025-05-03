package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.dto.input.PostInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameOutputDTOMapper;
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
public interface IPostInputDTOMapper {
    IPostInputDTOMapper INSTANCE = Mappers.getMapper(IPostInputDTOMapper.class);
    @Mapping(target = "user", source = "user")
    @Mapping(target = "game", source = "game")
    Post toDomain(PostInputDTO dto);
    @Mapping(target = "game", source = "game")
    @Mapping(target = "user", source = "user")
    PostInputDTO toDTO(Post domain);
    List<Post> toDomainList(List<PostInputDTO> dtos);
    List<PostInputDTO> toDTOList(List<Post> domains);


}