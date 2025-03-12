package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Genre;

import es.iespuertodelacruz.xptrade.dto.GenreDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IGenreDTOMapperTest extends MapperDTOHelper {
    Genre domainMapper;
    GenreDTO dtoMapper;
    List<GenreDTO> dtoListMapper;
    List<Genre> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IGenreDTOMapper.INSTANCE.toDTOList(genreDomains);
        Assertions.assertEquals(genreDTOList, dtoListMapper, MESSAGE_ERROR);

        genreDomains = null;
        dtoListMapper = IGenreDTOMapper.INSTANCE.toDTOList(genreDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IGenreDTOMapper.INSTANCE.toDomainList(genreDTOList);


        Assertions.assertEquals(genreDomains, domainListMapper, MESSAGE_ERROR);

        genreDTOList = null;
        domainListMapper = IGenreDTOMapper.INSTANCE.toDomainList(genreDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IGenreDTOMapper.INSTANCE.toDTO(genreDomain);

        Assertions.assertEquals(genreDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(genreDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IGenreDTOMapper.INSTANCE.toDTO(genreDomain);

        genreDomain = null;
        dtoMapper = IGenreDTOMapper.INSTANCE.toDTO(genreDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IGenreDTOMapper.INSTANCE.toDomain(genreDTO);

        Assertions.assertEquals(genreDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(genreDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        genreDTO = null;
        domainMapper = IGenreDTOMapper.INSTANCE.toDomain(genreDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
