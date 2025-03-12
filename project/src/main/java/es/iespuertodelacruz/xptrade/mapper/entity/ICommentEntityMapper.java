package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.model.entities.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserEntityMapper.class, IPostEntityMapper.class})
public interface ICommentEntityMapper {
    ICommentEntityMapper INSTANCE = Mappers.getMapper(ICommentEntityMapper.class);
    Comment toDomain(CommentEntity entity);
    CommentEntity toEntity(Comment domain);
    List<Comment> toDomainList(List<CommentEntity> entities);
    List<CommentEntity> toEntityList(List<Comment> domains);


}