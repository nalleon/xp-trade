package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.GameCollection;
import es.iespuertodelacruz.xptrade.model.entities.GameCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {ICollectionEntityMapper.class, IGameEntityMapper.class,
        IRegionEntityMapper.class, IPlatformEntityMapper.class})
public interface IGameCollectionEntityMapper {
    IGameCollectionEntityMapper INSTANCE = Mappers.getMapper(IGameCollectionEntityMapper.class);
    GameCollection toDomain(GameCollectionEntity entity);
    GameCollectionEntity toEntity(GameCollection domain);
    List<GameCollection> toDomainList(List<GameCollectionEntity> entities);
    List<GameCollectionEntity> toEntityList(List<GameCollection> domains);
}
