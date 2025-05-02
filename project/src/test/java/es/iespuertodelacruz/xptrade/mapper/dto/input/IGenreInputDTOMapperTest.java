package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.dto.input.GenreInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IGenreInputDTOMapperTest extends MapperInputDTOHelper {
    Genre domainMapper;
    GenreInputDTO dtoMapper;
    List<GenreInputDTO> dtoListMapper;
    List<Genre> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IGenreInputDTOMapper.INSTANCE.toDTOList(genreDomains);
        Assertions.assertEquals(genreInputDTOList, dtoListMapper, MESSAGE_ERROR);

        genreDomains = null;
        dtoListMapper = IGenreInputDTOMapper.INSTANCE.toDTOList(genreDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGenreInputDTOMapper.INSTANCE.toDomainList(genreInputDTOList);


        Assertions.assertEquals(genreDomains, domainListMapper, MESSAGE_ERROR);

        genreInputDTOList = null;
        domainListMapper = IGenreInputDTOMapper.INSTANCE.toDomainList(genreInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IGenreInputDTOMapper.INSTANCE.toDTO(genreDomain);

        Assertions.assertEquals(genreInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IGenreInputDTOMapper.INSTANCE.toDTO(genreDomain);

        genreDomain = null;
        dtoMapper = IGenreInputDTOMapper.INSTANCE.toDTO(genreDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IGenreInputDTOMapper.INSTANCE.toDomain(genreInputDTO);

        Assertions.assertEquals(genreDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(genreDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        genreInputDTO = null;
        domainMapper = IGenreInputDTOMapper.INSTANCE.toDomain(genreInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
