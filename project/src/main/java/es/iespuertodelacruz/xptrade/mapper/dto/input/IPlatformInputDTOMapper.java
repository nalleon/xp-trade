package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.dto.input.PlatformInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPlatformInputDTOMapper {
    IPlatformInputDTOMapper INSTANCE = Mappers.getMapper(IPlatformInputDTOMapper.class);
    Platform toDomain(PlatformInputDTO dto);
    PlatformInputDTO toDTO(Platform domain);
    List<Platform> toDomainList(List<PlatformInputDTO> dtos);
    List<PlatformInputDTO> toDTOList(List<Platform> domains);
}
