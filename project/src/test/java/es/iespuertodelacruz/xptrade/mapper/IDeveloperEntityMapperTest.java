package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.model.entities.DeveloperEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IDeveloperEntityMapperTest extends MapperHelper {
    Developer domainMapper;
    DeveloperEntity entityMapper;

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
