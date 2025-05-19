package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.GameCollection;
import es.iespuertodelacruz.xptrade.dto.output.GameCollectionOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper(uses = {IGameOutputDTOMapper.class, IRegionOutputDTOMapper.class, IPlatformOutputDTOMapper.class})
public interface IGameCollectionOutputDTOMapper {
    IGameCollectionOutputDTOMapper INSTANCE = Mappers.getMapper(IGameCollectionOutputDTOMapper.class);
    GameCollection toDomain(GameCollectionOutputDTO dto);
    GameCollectionOutputDTO toDTO(GameCollection domain);
    List<GameCollection> toDomainList(List<GameCollectionOutputDTO> dtos);
    List<GameCollectionOutputDTO> toDTOList(List<GameCollection> domains);
}
