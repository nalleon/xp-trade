package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.dto.PublisherDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPublisherDTOMapper {
    IPublisherDTOMapper INSTANCE = Mappers.getMapper(IPublisherDTOMapper.class);
    Publisher toDomain(PublisherDTO dto);
    PublisherDTO toDTO(Publisher domain);
    List<Publisher> toDomainList(List<PublisherDTO> dtos);
    List<PublisherDTO> toDTOList(List<Publisher> domains);
}
