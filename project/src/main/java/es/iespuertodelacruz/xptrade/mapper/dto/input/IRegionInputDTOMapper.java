package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.dto.input.RegionInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IRegionInputDTOMapper {
    IRegionInputDTOMapper INSTANCE = Mappers.getMapper(IRegionInputDTOMapper.class);
    Region toDomain(RegionInputDTO dto);
    RegionInputDTO toDTO(Region domain);
    List<Region> toDomainList(List<RegionInputDTO> dtos);
    List<RegionInputDTO> toDTOList(List<Region> domains);
}
