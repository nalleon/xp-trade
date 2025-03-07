package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.model.entities.PublisherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IPublisherEntityMapper {
    IPublisherEntityMapper INSTANCE = Mappers.getMapper(IPublisherEntityMapper.class);
    Publisher toDomain(PublisherEntity entity);
    PublisherEntity toEntity(Publisher domain);
    List<Publisher> toDomainList(List<PublisherEntity> entities);
    List<PublisherEntity> toEntityList(List<Publisher> domains);
}
