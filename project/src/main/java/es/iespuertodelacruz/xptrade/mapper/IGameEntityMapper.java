package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IPlatformEntityMapper.class, IDeveloperEntityMapper.class, IRegionEntityMapper.class, 
        IPublisherEntityMapper.class, IGenreEntityMapper.class})
public interface IGameEntityMapper {
    IGameEntityMapper INSTANCE = Mappers.getMapper(IGameEntityMapper.class);
    Game toDomain(GameEntity entity);
    GameEntity toEntity(Game domain);
    List<Game> toDomainList(List<GameEntity> entities);
    List<GameEntity> toEntityList(List<Game> domains);


}