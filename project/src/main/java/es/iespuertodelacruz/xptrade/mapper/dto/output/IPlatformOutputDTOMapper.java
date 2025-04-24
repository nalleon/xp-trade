package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.dto.output.PlatformOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper
public interface IPlatformOutputDTOMapper {
    IPlatformOutputDTOMapper INSTANCE = Mappers.getMapper(IPlatformOutputDTOMapper.class);
    Platform toDomain(PlatformOutputDTO dto);
    PlatformOutputDTO toDTO(Platform domain);
    List<Platform> toDomainList(List<PlatformOutputDTO> dtos);
    List<PlatformOutputDTO> toDTOList(List<Platform> domains);
}
