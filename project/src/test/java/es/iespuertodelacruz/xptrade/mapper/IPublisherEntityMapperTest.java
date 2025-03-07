package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.model.entities.PublisherEntity;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IPublisherEntityMapperTest extends MapperHelper {
    Publisher domainMapper;
    PublisherEntity entityMapper;

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
