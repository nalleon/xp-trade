package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.model.entities.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IGenreEntityMapper {
    IGenreEntityMapper INSTANCE = Mappers.getMapper(IGenreEntityMapper.class);
    Genre toDomain(GenreEntity entity);
    GenreEntity toEntity(Genre domain);
    List<Genre> toDomainList(List<GenreEntity> entities);
    List<GenreEntity> toEntityList(List<Genre> domains);
}
