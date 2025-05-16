package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICollectionEntityMapperTest extends MapperHelper {
    Collection domainMapper;
    CollectionEntity entityMapper;
    List<CollectionEntity> entityListMapper;
    List<Collection> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = ICollectionEntityMapper.INSTANCE.toEntityList(collectionDomains);
        Assertions.assertEquals(collectionEntities, entityListMapper, MESSAGE_ERROR);

        collectionDomains = null;
        entityListMapper = ICollectionEntityMapper.INSTANCE.toEntityList(collectionDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICollectionEntityMapper.INSTANCE.toDomainList(collectionEntities);
        Assertions.assertEquals(collectionDomains, domainListMapper, MESSAGE_ERROR);

        collectionEntities = null;
        domainListMapper = ICollectionEntityMapper.INSTANCE.toDomainList(collectionEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toEntityTest(){
        entityMapper = ICollectionEntityMapper.INSTANCE.toEntity(collectionDomain);

        Assertions.assertEquals(collectionEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionEntity.getUser(), entityMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionEntity.getGameCollection(), entityMapper.getGameCollection(), MESSAGE_ERROR);

        entityMapper = ICollectionEntityMapper.INSTANCE.toEntity(collectionDomain);

        collectionDomain = null;
        entityMapper = ICollectionEntityMapper.INSTANCE.toEntity(collectionDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICollectionEntityMapper.INSTANCE.toDomain(collectionEntity);

        Assertions.assertEquals(collectionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);

        collectionEntity = null;
        domainMapper = ICollectionEntityMapper.INSTANCE.toDomain(collectionEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
