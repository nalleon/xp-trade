package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IRoleEntityMapper.class})
public interface IUserEntityMapper {
    IUserEntityMapper INSTANCE = Mappers.getMapper(IUserEntityMapper.class);
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
    List<User> toDomainList(List<UserEntity> entities);
    List<UserEntity> toEntityList(List<User> domains);


}