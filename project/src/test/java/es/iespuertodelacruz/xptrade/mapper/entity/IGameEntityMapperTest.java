package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IGameEntityMapperTest extends MapperHelper {
    Game domainMapper;
    GameEntity entityMapper;
    List<GameEntity> entityListMapper;
    List<Game> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IGameEntityMapper.INSTANCE.toEntityList(gameDomains);
        Assertions.assertEquals(gameEntities, entityListMapper, MESSAGE_ERROR);

        gameDomains = null;
        entityListMapper = IGameEntityMapper.INSTANCE.toEntityList(gameDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGameEntityMapper.INSTANCE.toDomainList(gameEntities);
        Assertions.assertEquals(gameDomains, domainListMapper, MESSAGE_ERROR);

        gameEntities = null;
        domainListMapper = IGameEntityMapper.INSTANCE.toDomainList(gameEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toEntityTest(){
        entityMapper = IGameEntityMapper.INSTANCE.toEntity(gameDomain);

        Assertions.assertEquals(gameEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getTitle(), entityMapper.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getSlug(), entityMapper.getSlug(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getCoverArt(), entityMapper.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getDeveloperEntitySet(), entityMapper.getDeveloperEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getGenreEntitySet(), entityMapper.getGenreEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getPlatformEntitySet(), entityMapper.getPlatformEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity.getPublisherEntitySet(), entityMapper.getPublisherEntitySet(), MESSAGE_ERROR);

        gameDomain.setTagSet(null);
        gameDomain.setGenreSet(null);
        gameDomain.setPlatformSet(null);
        gameDomain.setDeveloperSet(null);
        gameDomain.setPublisherSet(null);

        entityMapper = IGameEntityMapper.INSTANCE.toEntity(gameDomain);
        Assertions.assertNull(entityMapper.getPublisherEntitySet(), MESSAGE_ERROR);
        Assertions.assertNull(entityMapper.getPlatformEntitySet(), MESSAGE_ERROR);
        Assertions.assertNull(entityMapper.getDeveloperEntitySet(), MESSAGE_ERROR);
        Assertions.assertNull(entityMapper.getGenreEntitySet(), MESSAGE_ERROR);
        Assertions.assertNull(entityMapper.getTagEntitySet(), MESSAGE_ERROR);


        gameDomain = null;
        entityMapper = IGameEntityMapper.INSTANCE.toEntity(gameDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IGameEntityMapper.INSTANCE.toDomain(gameEntity);

        Assertions.assertEquals(gameDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getTitle(), domainMapper.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getCoverArt(), domainMapper.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getSlug(), domainMapper.getSlug(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getDeveloperSet(), domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getGenreSet(), domainMapper.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPlatformSet(), domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain.getPublisherSet(), domainMapper.getPublisherSet(), MESSAGE_ERROR);

        gameEntity.setTagEntitySet(null);
        gameEntity.setGenreEntitySet(null);
        gameEntity.setPlatformEntitySet(null);
        gameEntity.setDeveloperEntitySet(null);
        gameEntity.setPublisherEntitySet(null);

        domainMapper = IGameEntityMapper.INSTANCE.toDomain(gameEntity);
        Assertions.assertNull(domainMapper.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getPublisherSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getTagSet(), MESSAGE_ERROR);

        gameEntity = null;
        domainMapper = IGameEntityMapper.INSTANCE.toDomain(gameEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
