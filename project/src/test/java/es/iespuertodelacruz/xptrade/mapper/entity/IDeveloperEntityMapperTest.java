package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.model.entities.DeveloperEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IDeveloperEntityMapperTest extends MapperHelper {
    Developer domainMapper;
    DeveloperEntity entityMapper;
    List<DeveloperEntity> entityListMapper;
    List<Developer> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IDeveloperEntityMapper.INSTANCE.toEntityList(developerDomains);
        Assertions.assertEquals(developerEntities, entityListMapper, MESSAGE_ERROR);

        developerDomains = null;
        entityListMapper = IDeveloperEntityMapper.INSTANCE.toEntityList(developerDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IDeveloperEntityMapper.INSTANCE.toDomainList(developerEntities);
        Assertions.assertEquals(developerDomains, domainListMapper, MESSAGE_ERROR);

        developerEntities = null;
        domainListMapper = IDeveloperEntityMapper.INSTANCE.toDomainList(developerEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toEntityTest(){
        entityMapper = IDeveloperEntityMapper.INSTANCE.toEntity(developerDomain);

        Assertions.assertEquals(developerEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(developerEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = IDeveloperEntityMapper.INSTANCE.toEntity(developerDomain);

        developerDomain = null;
        entityMapper = IDeveloperEntityMapper.INSTANCE.toEntity(developerDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IDeveloperEntityMapper.INSTANCE.toDomain(developerEntity);

        Assertions.assertEquals(developerDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(developerDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        developerEntity = null;
        domainMapper = IDeveloperEntityMapper.INSTANCE.toDomain(developerEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
