package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.mapper.entity.IPublisherEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.PublisherEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPublisherDTOMapperTest extends MapperHelper {
    Publisher domainMapper;
    PublisherEntity entityMapper;
    List<PublisherEntity> entityListMapper;
    List<Publisher> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IPublisherEntityMapper.INSTANCE.toEntityList(publisherDomains);
        Assertions.assertEquals(publisherEntities, entityListMapper, MESSAGE_ERROR);

        publisherDomains = null;
        entityListMapper = IPublisherEntityMapper.INSTANCE.toEntityList(publisherDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPublisherEntityMapper.INSTANCE.toDomainList(publisherEntities);


        Assertions.assertEquals(publisherDomains, domainListMapper, MESSAGE_ERROR);

        publisherEntities = null;
        domainListMapper = IPublisherEntityMapper.INSTANCE.toDomainList(publisherEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toEntityTest (){
        entityMapper = IPublisherEntityMapper.INSTANCE.toEntity(publisherDomain);

        Assertions.assertEquals(publisherEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = IPublisherEntityMapper.INSTANCE.toEntity(publisherDomain);

        publisherDomain = null;
        entityMapper = IPublisherEntityMapper.INSTANCE.toEntity(publisherDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPublisherEntityMapper.INSTANCE.toDomain(publisherEntity);

        Assertions.assertEquals(publisherDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        publisherEntity = null;
        domainMapper = IPublisherEntityMapper.INSTANCE.toDomain(publisherEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
