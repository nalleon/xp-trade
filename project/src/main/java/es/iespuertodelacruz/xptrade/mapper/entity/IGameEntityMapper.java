package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IPlatformEntityMapper.class, IDeveloperEntityMapper.class,
        IPublisherEntityMapper.class, IGenreEntityMapper.class})
public interface IGameEntityMapper {
    IGameEntityMapper INSTANCE = Mappers.getMapper(IGameEntityMapper.class);
    @Mapping(target = "developerSet", source = "developerEntitySet")
    @Mapping(target = "genreSet", source = "genreEntitySet")
    @Mapping(target = "platformSet", source = "platformEntitySet")
    @Mapping(target = "publisherSet", source = "publisherEntitySet")
    Game toDomain(GameEntity entity);

    @Mapping(target = "developerEntitySet", source = "developerSet")
    @Mapping(target = "genreEntitySet", source = "genreSet")
    @Mapping(target = "platformEntitySet", source = "platformSet")
    @Mapping(target = "publisherEntitySet", source = "publisherSet")
    GameEntity toEntity(Game domain);
    List<Game> toDomainList(List<GameEntity> entities);
    List<GameEntity> toEntityList(List<Game> domains);
}