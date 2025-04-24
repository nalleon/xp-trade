package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.dto.output.RegionOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper
public interface IRegionOutputDTOMapper {
    IRegionOutputDTOMapper INSTANCE = Mappers.getMapper(IRegionOutputDTOMapper.class);
    Region toDomain(RegionOutputDTO dto);
    RegionOutputDTO toDTO(Region domain);
    List<Region> toDomainList(List<RegionOutputDTO> dtos);
    List<RegionOutputDTO> toDTOList(List<Region> domains);
}
