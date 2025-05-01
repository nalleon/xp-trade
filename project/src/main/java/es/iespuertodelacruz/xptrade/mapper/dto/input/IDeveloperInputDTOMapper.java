package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.dto.input.DeveloperInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IDeveloperInputDTOMapper {
    IDeveloperInputDTOMapper INSTANCE = Mappers.getMapper(IDeveloperInputDTOMapper.class);
    Developer toDomain(DeveloperInputDTO dto);
    DeveloperInputDTO toDTO(Developer domain);
    List<Developer> toDomainList(List<DeveloperInputDTO> dtos);
    List<DeveloperInputDTO> toDTOList(List<Developer> domains);
}
