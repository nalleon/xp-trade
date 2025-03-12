package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.model.entities.FavoriteEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IFavoriteEntityMapperTest extends MapperHelper {
    Favorite domainMapper;
    FavoriteEntity entityMapper;
    List<FavoriteEntity> entityListMapper;
    List<Favorite> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IFavoriteEntityMapper.INSTANCE.toEntityList(favoriteDomains);
        Assertions.assertEquals(favoriteEntities, entityListMapper, MESSAGE_ERROR);

        favoriteDomains = null;
        entityListMapper = IFavoriteEntityMapper.INSTANCE.toEntityList(favoriteDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IFavoriteEntityMapper.INSTANCE.toDomainList(favoriteEntities);
        Assertions.assertEquals(favoriteDomains, domainListMapper, MESSAGE_ERROR);

        favoriteEntities = null;
        domainListMapper = IFavoriteEntityMapper.INSTANCE.toDomainList(favoriteEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toEntityTest(){
        entityMapper = IFavoriteEntityMapper.INSTANCE.toEntity(favoriteDomain);

        Assertions.assertEquals(favoriteEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteEntity.getUser(), entityMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteEntity.getGame(), entityMapper.getGame(), MESSAGE_ERROR);

        entityMapper = IFavoriteEntityMapper.INSTANCE.toEntity(favoriteDomain);

        favoriteDomain = null;
        entityMapper = IFavoriteEntityMapper.INSTANCE.toEntity(favoriteDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IFavoriteEntityMapper.INSTANCE.toDomain(favoriteEntity);

        Assertions.assertEquals(favoriteDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(favoriteDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);

        favoriteEntity = null;
        domainMapper = IFavoriteEntityMapper.INSTANCE.toDomain(favoriteEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
