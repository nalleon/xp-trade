package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.CollectionDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserSearchDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserSearchDTOMapper.class, IGameDTOMapper.class})
public interface ICollectionDTOMapper {
    ICollectionDTOMapper INSTANCE = Mappers.getMapper(ICollectionDTOMapper.class);
    Collection toDomain(CollectionDTO dto);
    CollectionDTO toDTO(Collection domain);
    List<Collection> toDomainList(List<CollectionDTO> dtos);
    List<CollectionDTO> toDTOList(List<Collection> domains);


}