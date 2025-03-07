package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.model.entities.DeveloperEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IDeveloperEntityMapper {
    IDeveloperEntityMapper INSTANCE = Mappers.getMapper(IDeveloperEntityMapper.class);
    Developer toDomain(DeveloperEntity entity);
    DeveloperEntity toEntity(Developer domain);
    List<Developer> toDomainList(List<DeveloperEntity> entities);
    List<DeveloperEntity> toEntityList(List<Developer> domains);
}
