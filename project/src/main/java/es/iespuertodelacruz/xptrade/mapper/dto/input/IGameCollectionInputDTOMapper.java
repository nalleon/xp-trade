package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.GameCollection;
import es.iespuertodelacruz.xptrade.dto.input.GameCollectionInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {IGameInputDTOMapper.class, IRegionInputDTOMapper.class, IPlatformInputDTOMapper.class})
public interface IGameCollectionInputDTOMapper {
    IGameCollectionInputDTOMapper INSTANCE = Mappers.getMapper(es.iespuertodelacruz.xptrade.mapper.dto.input.IGameCollectionInputDTOMapper.class);
    GameCollection toDomain(GameCollectionInputDTO dto);
    GameCollectionInputDTO toDTO(GameCollection domain);
    List<GameCollection> toDomainList(List<GameCollectionInputDTO> dtos);
    List<GameCollectionInputDTO> toDTOList(List<GameCollection> domains);
}
