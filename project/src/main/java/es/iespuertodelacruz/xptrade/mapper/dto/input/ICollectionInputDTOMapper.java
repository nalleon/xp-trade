package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.input.CollectionInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserDTOMapper.class, IGameOutputDTOMapper.class})
public interface ICollectionInputDTOMapper {
    ICollectionInputDTOMapper INSTANCE = Mappers.getMapper(ICollectionInputDTOMapper.class);
    Collection toDomain(CollectionInputDTO dto);
    CollectionInputDTO toDTO(Collection domain);
    List<Collection> toDomainList(List<CollectionInputDTO> dtos);
    List<CollectionInputDTO> toDTOList(List<Collection> domains);


}