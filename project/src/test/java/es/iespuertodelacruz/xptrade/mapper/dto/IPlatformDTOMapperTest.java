package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Platform;

import es.iespuertodelacruz.xptrade.dto.PlatformDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPlatformDTOMapperTest extends MapperDTOHelper {
    Platform domainMapper;
    PlatformDTO dtoMapper;
    List<PlatformDTO> dtoListMapper;
    List<Platform> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPlatformDTOMapper.INSTANCE.toDTOList(platformDomains);
        Assertions.assertEquals(platformDTOList, dtoListMapper, MESSAGE_ERROR);

        platformDomains = null;
        dtoListMapper = IPlatformDTOMapper.INSTANCE.toDTOList(platformDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPlatformDTOMapper.INSTANCE.toDomainList(platformDTOList);


        Assertions.assertEquals(platformDomains, domainListMapper, MESSAGE_ERROR);

        platformDTOList = null;
        domainListMapper = IPlatformDTOMapper.INSTANCE.toDomainList(platformDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IPlatformDTOMapper.INSTANCE.toDTO(platformDomain);

        Assertions.assertEquals(platformDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(platformDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IPlatformDTOMapper.INSTANCE.toDTO(platformDomain);

        platformDomain = null;
        dtoMapper = IPlatformDTOMapper.INSTANCE.toDTO(platformDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPlatformDTOMapper.INSTANCE.toDomain(platformDTO);

        Assertions.assertEquals(platformDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(platformDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        platformDTO = null;
        domainMapper = IPlatformDTOMapper.INSTANCE.toDomain(platformDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
