package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.model.entities.PostEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPostEntityMapperTest extends MapperHelper {
    Post domainMapper;
    PostEntity entityMapper;
    List<PostEntity> entityListMapper;
    List<Post> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IPostEntityMapper.INSTANCE.toEntityList(postDomains);
        Assertions.assertEquals(postEntities, entityListMapper, MESSAGE_ERROR);

        postDomains = null;
        entityListMapper = IPostEntityMapper.INSTANCE.toEntityList(postDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPostEntityMapper.INSTANCE.toDomainList(postEntities);
        Assertions.assertEquals(postDomains, domainListMapper, MESSAGE_ERROR);

        postEntities = null;
        domainListMapper = IPostEntityMapper.INSTANCE.toDomainList(postEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toEntityTest(){
        entityMapper = IPostEntityMapper.INSTANCE.toEntity(postDomain);

        Assertions.assertEquals(postEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(postEntity.getUser(), entityMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(postEntity.getGame(), entityMapper.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(postEntity.getContent(), entityMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(postEntity.getCreationDate(), entityMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postEntity.getPicture(), entityMapper.getPicture(), MESSAGE_ERROR);

        entityMapper = IPostEntityMapper.INSTANCE.toEntity(postDomain);

        postDomain = null;
        entityMapper = IPostEntityMapper.INSTANCE.toEntity(postDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IPostEntityMapper.INSTANCE.toDomain(postEntity);

        Assertions.assertEquals(postDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getPicture(), domainMapper.getPicture(), MESSAGE_ERROR);
        postEntity = null;
        domainMapper = IPostEntityMapper.INSTANCE.toDomain(postEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
