package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserEntityMapper.class, IGameEntityMapper.class})
public interface ICollectionEntityMapper {
    ICollectionEntityMapper INSTANCE = Mappers.getMapper(ICollectionEntityMapper.class);
    Collection toDomain(CollectionEntity entity);
    CollectionEntity toEntity(Collection domain);
    List<Collection> toDomainList(List<CollectionEntity> entities);
    List<CollectionEntity> toEntityList(List<Collection> domains);


}