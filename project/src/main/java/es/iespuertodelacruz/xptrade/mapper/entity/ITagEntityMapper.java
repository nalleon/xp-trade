package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.model.entities.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface ITagEntityMapper {
    ITagEntityMapper INSTANCE = Mappers.getMapper(ITagEntityMapper.class);
    Tag toDomain(TagEntity entity);
    TagEntity toEntity(Tag domain);
    List<Tag> toDomainList(List<TagEntity> entities);
    List<TagEntity> toEntityList(List<Tag> domains);
}
