package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.model.entities.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper(uses = {IUserEntityMapper.class, IGameEntityMapper.class})
public interface IPostEntityMapper {
    IPostEntityMapper INSTANCE = Mappers.getMapper(IPostEntityMapper.class);
    Post toDomain(PostEntity entity);
    PostEntity toEntity(Post domain);
    List<Post> toDomainList(List<PostEntity> entities);
    List<PostEntity> toEntityList(List<Post> domains);


}