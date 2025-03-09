package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.model.entities.CommentEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICommentEntityMapperTest extends MapperHelper {
    Comment domainMapper;
    CommentEntity entityMapper;
    List<CommentEntity> entityListMapper;
    List<Comment> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = ICommentEntityMapper.INSTANCE.toEntityList(commentDomains);
        Assertions.assertEquals(commentEntities, entityListMapper, MESSAGE_ERROR);

        commentDomains = null;
        entityListMapper = ICommentEntityMapper.INSTANCE.toEntityList(commentDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICommentEntityMapper.INSTANCE.toDomainList(commentEntities);
        Assertions.assertEquals(commentDomains, domainListMapper, MESSAGE_ERROR);

        commentEntities = null;
        domainListMapper = ICommentEntityMapper.INSTANCE.toDomainList(commentEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toEntityTest(){
        entityMapper = ICommentEntityMapper.INSTANCE.toEntity(commentDomain);

        Assertions.assertEquals(commentEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(commentEntity.getUser(), entityMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(commentEntity.getContent(), entityMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(commentEntity.getCreationDate(), entityMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(commentEntity.getPost(), entityMapper.getPost(), MESSAGE_ERROR);

        entityMapper = ICommentEntityMapper.INSTANCE.toEntity(commentDomain);

        commentDomain = null;
        entityMapper = ICommentEntityMapper.INSTANCE.toEntity(commentDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICommentEntityMapper.INSTANCE.toDomain(commentEntity);

        Assertions.assertEquals(commentDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getPost(), domainMapper.getPost(), MESSAGE_ERROR);
        commentEntity = null;
        domainMapper = ICommentEntityMapper.INSTANCE.toDomain(commentEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
