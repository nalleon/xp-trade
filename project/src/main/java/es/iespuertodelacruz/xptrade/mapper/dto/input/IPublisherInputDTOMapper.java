package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.dto.input.PublisherInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPublisherInputDTOMapper {
    IPublisherInputDTOMapper INSTANCE = Mappers.getMapper(IPublisherInputDTOMapper.class);
    Publisher toDomain(PublisherInputDTO dto);
    PublisherInputDTO toDTO(Publisher domain);
    List<Publisher> toDomainList(List<PublisherInputDTO> dtos);
    List<PublisherInputDTO> toDTOList(List<Publisher> domains);
}
