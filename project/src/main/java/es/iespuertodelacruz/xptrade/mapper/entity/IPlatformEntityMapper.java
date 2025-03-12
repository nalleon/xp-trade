package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.model.entities.PlatformEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPlatformEntityMapper {
    IPlatformEntityMapper INSTANCE = Mappers.getMapper(IPlatformEntityMapper.class);
    Platform toDomain(PlatformEntity entity);
    PlatformEntity toEntity(Platform domain);
    List<Platform> toDomainList(List<PlatformEntity> entities);
    List<PlatformEntity> toEntityList(List<Platform> domains);
}
