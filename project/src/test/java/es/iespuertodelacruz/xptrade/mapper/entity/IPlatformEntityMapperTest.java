package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.model.entities.PlatformEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPlatformEntityMapperTest extends MapperHelper {
    Platform domainMapper;
    PlatformEntity entityMapper;
    List<PlatformEntity> entityListMapper;
    List<Platform> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IPlatformEntityMapper.INSTANCE.toEntityList(platformDomains);
        Assertions.assertEquals(platformEntities, entityListMapper, MESSAGE_ERROR);

        platformDomains = null;
        entityListMapper = IPlatformEntityMapper.INSTANCE.toEntityList(platformDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPlatformEntityMapper.INSTANCE.toDomainList(platformEntities);


        Assertions.assertEquals(platformDomains, domainListMapper, MESSAGE_ERROR);

        platformEntities = null;
        domainListMapper = IPlatformEntityMapper.INSTANCE.toDomainList(platformEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toEntityTest (){
        entityMapper = IPlatformEntityMapper.INSTANCE.toEntity(platformDomain);

        Assertions.assertEquals(platformEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(platformEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = IPlatformEntityMapper.INSTANCE.toEntity(platformDomain);

        platformDomain = null;
        entityMapper = IPlatformEntityMapper.INSTANCE.toEntity(platformDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPlatformEntityMapper.INSTANCE.toDomain(platformEntity);

        Assertions.assertEquals(platformDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(platformDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        platformEntity = null;
        domainMapper = IPlatformEntityMapper.INSTANCE.toDomain(platformEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
