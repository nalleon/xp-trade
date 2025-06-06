package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IRoleEntityMapper {
    IRoleEntityMapper INSTANCE = Mappers.getMapper(IRoleEntityMapper.class);
    Role toDomain(RoleEntity entity);
    RoleEntity toEntity(Role domain);
    List<Role> toDomainList(List<RoleEntity> entities);
    List<RoleEntity> toEntityList(List<Role> domains);


}
