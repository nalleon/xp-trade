package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.dto.output.PublisherOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPublisherOutputDTOMapper {
    IPublisherOutputDTOMapper INSTANCE = Mappers.getMapper(IPublisherOutputDTOMapper.class);
    Publisher toDomain(PublisherOutputDTO dto);
    PublisherOutputDTO toDTO(Publisher domain);
    List<Publisher> toDomainList(List<PublisherOutputDTO> dtos);
    List<PublisherOutputDTO> toDTOList(List<Publisher> domains);
}
