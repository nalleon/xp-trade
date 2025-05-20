package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.model.entities.TagEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ITagEntityMapperTest extends MapperHelper {
    Tag domainMapper;
    TagEntity entityMapper;
    List<TagEntity> entityListMapper;
    List<Tag> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = ITagEntityMapper.INSTANCE.toEntityList(tagDomains);
        Assertions.assertEquals(tagEntities, entityListMapper, MESSAGE_ERROR);

        tagDomains = null;
        entityListMapper = ITagEntityMapper.INSTANCE.toEntityList(tagDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ITagEntityMapper.INSTANCE.toDomainList(tagEntities);


        Assertions.assertEquals(tagDomains, domainListMapper, MESSAGE_ERROR);

        tagEntities = null;
        domainListMapper = ITagEntityMapper.INSTANCE.toDomainList(tagEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toEntityTest (){
        entityMapper = ITagEntityMapper.INSTANCE.toEntity(tagDomain);

        Assertions.assertEquals(tagEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(tagEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = ITagEntityMapper.INSTANCE.toEntity(tagDomain);

        tagDomain = null;
        entityMapper = ITagEntityMapper.INSTANCE.toEntity(tagDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = ITagEntityMapper.INSTANCE.toDomain(tagEntity);

        Assertions.assertEquals(tagDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(tagDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        tagEntity = null;
        domainMapper = ITagEntityMapper.INSTANCE.toDomain(tagEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
