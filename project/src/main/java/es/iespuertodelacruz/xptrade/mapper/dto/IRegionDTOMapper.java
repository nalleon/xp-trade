package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.dto.RegionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IRegionDTOMapper {
    IRegionDTOMapper INSTANCE = Mappers.getMapper(IRegionDTOMapper.class);
    Region toDomain(RegionDTO dto);
    RegionDTO toDTO(Region domain);
    List<Region> toDomainList(List<RegionDTO> dtos);
    List<RegionDTO> toDTOList(List<Region> domains);
}
