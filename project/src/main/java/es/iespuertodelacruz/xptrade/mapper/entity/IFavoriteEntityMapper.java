package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.model.entities.FavoriteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper(uses = {IUserEntityMapper.class, IGameEntityMapper.class})
public interface IFavoriteEntityMapper {
    IFavoriteEntityMapper INSTANCE = Mappers.getMapper(IFavoriteEntityMapper.class);
    Favorite toDomain(FavoriteEntity entity);
    FavoriteEntity toEntity(Favorite domain);
    List<Favorite> toDomainList(List<FavoriteEntity> entities);
    List<FavoriteEntity> toEntityList(List<Favorite> domains);


}