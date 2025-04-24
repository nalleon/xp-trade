package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper(uses = {IUserDTOMapper.class, IGameOutputDTOMapper.class})
public interface ICollectionOutputDTOMapper {
    ICollectionOutputDTOMapper INSTANCE = Mappers.getMapper(ICollectionOutputDTOMapper.class);
    Collection toDomain(CollectionOutputDTO dto);
    CollectionOutputDTO toDTO(Collection domain);
    List<Collection> toDomainList(List<CollectionOutputDTO> dtos);
    List<CollectionOutputDTO> toDTOList(List<Collection> domains);


}