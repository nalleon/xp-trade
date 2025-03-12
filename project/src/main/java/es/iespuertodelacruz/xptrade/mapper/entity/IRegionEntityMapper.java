package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.model.entities.RegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IRegionEntityMapper {
    IRegionEntityMapper INSTANCE = Mappers.getMapper(IRegionEntityMapper.class);
    Region toDomain(RegionEntity entity);
    RegionEntity toEntity(Region domain);
    List<Region> toDomainList(List<RegionEntity> entities);
    List<RegionEntity> toEntityList(List<Region> domains);
}
