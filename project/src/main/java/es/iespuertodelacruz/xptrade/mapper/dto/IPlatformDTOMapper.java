package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.dto.PlatformDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPlatformDTOMapper {
    IPlatformDTOMapper INSTANCE = Mappers.getMapper(IPlatformDTOMapper.class);
    Platform toDomain(PlatformDTO dto);
    PlatformDTO toDTO(Platform domain);
    List<Platform> toDomainList(List<PlatformDTO> dtos);
    List<PlatformDTO> toDTOList(List<Platform> domains);
}
