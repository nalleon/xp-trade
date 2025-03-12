package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Developer;

import es.iespuertodelacruz.xptrade.dto.DeveloperDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IDeveloperDTOMapper {
    IDeveloperDTOMapper INSTANCE = Mappers.getMapper(IDeveloperDTOMapper.class);
    Developer toDomain(DeveloperDTO dto);
    DeveloperDTO toDTO(Developer domain);
    List<Developer> toDomainList(List<DeveloperDTO> dtos);
    List<DeveloperDTO> toDTOList(List<Developer> domains);
}
