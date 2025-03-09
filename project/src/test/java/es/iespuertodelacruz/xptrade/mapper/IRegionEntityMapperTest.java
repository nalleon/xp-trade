package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.model.entities.RegionEntity;
import es.iespuertodelacruz.xptrade.model.entities.RegionEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRegionEntityMapperTest extends MapperHelper {
    Region domainMapper;
    RegionEntity entityMapper;
    List<RegionEntity> entityListMapper;
    List<Region> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IRegionEntityMapper.INSTANCE.toEntityList(regionDomains);
        Assertions.assertEquals(regionEntities, entityListMapper, MESSAGE_ERROR);

        regionDomains = null;
        entityListMapper = IRegionEntityMapper.INSTANCE.toEntityList(regionDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRegionEntityMapper.INSTANCE.toDomainList(regionEntities);


        Assertions.assertEquals(regionDomains, domainListMapper, MESSAGE_ERROR);

        regionEntities = null;
        domainListMapper = IRegionEntityMapper.INSTANCE.toDomainList(regionEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toEntityTest (){
        entityMapper = IRegionEntityMapper.INSTANCE.toEntity(regionDomain);

        Assertions.assertEquals(regionEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(regionEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = IRegionEntityMapper.INSTANCE.toEntity(regionDomain);

        regionDomain = null;
        entityMapper = IRegionEntityMapper.INSTANCE.toEntity(regionDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IRegionEntityMapper.INSTANCE.toDomain(regionEntity);

        Assertions.assertEquals(regionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(regionDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);

        regionEntity = null;
        domainMapper = IRegionEntityMapper.INSTANCE.toDomain(regionEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
