package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Genre;

import es.iespuertodelacruz.xptrade.dto.output.GenreOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IGenreOutputDTOMapperTest extends MapperDTOHelper {
    Genre domainMapper;
    GenreOutputDTO dtoMapper;
    List<GenreOutputDTO> dtoListMapper;
    List<Genre> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IGenreOutputDTOMapper.INSTANCE.toDTOList(genreDomains);
        Assertions.assertEquals(genreOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        genreDomains = null;
        dtoListMapper = IGenreOutputDTOMapper.INSTANCE.toDTOList(genreDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGenreOutputDTOMapper.INSTANCE.toDomainList(genreOutputDTOList);


        Assertions.assertEquals(genreDomains, domainListMapper, MESSAGE_ERROR);

        genreOutputDTOList = null;
        domainListMapper = IGenreOutputDTOMapper.INSTANCE.toDomainList(genreOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IGenreOutputDTOMapper.INSTANCE.toDTO(genreDomain);

        Assertions.assertEquals(genreOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(genreOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IGenreOutputDTOMapper.INSTANCE.toDTO(genreDomain);

        genreDomain = null;
        dtoMapper = IGenreOutputDTOMapper.INSTANCE.toDTO(genreDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IGenreOutputDTOMapper.INSTANCE.toDomain(genreOutputDTO);

        Assertions.assertEquals(genreDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(genreDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        genreOutputDTO = null;
        domainMapper = IGenreOutputDTOMapper.INSTANCE.toDomain(genreOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
