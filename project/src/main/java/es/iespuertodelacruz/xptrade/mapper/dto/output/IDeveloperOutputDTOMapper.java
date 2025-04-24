package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Developer;

import es.iespuertodelacruz.xptrade.dto.output.DeveloperOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper
public interface IDeveloperOutputDTOMapper {
    IDeveloperOutputDTOMapper INSTANCE = Mappers.getMapper(IDeveloperOutputDTOMapper.class);
    Developer toDomain(DeveloperOutputDTO dto);
    DeveloperOutputDTO toDTO(Developer domain);
    List<Developer> toDomainList(List<DeveloperOutputDTO> dtos);
    List<DeveloperOutputDTO> toDTOList(List<Developer> domains);
}
