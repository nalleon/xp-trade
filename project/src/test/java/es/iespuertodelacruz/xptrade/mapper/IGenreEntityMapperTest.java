package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.model.entities.GenreEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IGenreEntityMapperTest extends MapperHelper {
    Genre domainMapper;
    GenreEntity entityMapper;

    @Test
    public void  toEntityTest (){
        entityMapper = IGenreEntityMapper.INSTANCE.toEntity(genreDomain);

        Assertions.assertEquals(genreEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(genreEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = IGenreEntityMapper.INSTANCE.toEntity(genreDomain);

        genreDomain = null;
        entityMapper = IGenreEntityMapper.INSTANCE.toEntity(genreDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IGenreEntityMapper.INSTANCE.toDomain(genreEntity);

        Assertions.assertEquals(genreDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(genreDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        genreEntity = null;
        domainMapper = IGenreEntityMapper.INSTANCE.toDomain(genreEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
