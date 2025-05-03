package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Platform;

import es.iespuertodelacruz.xptrade.dto.output.PlatformOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPlatformOutputDTOMapperTest extends MapperDTOHelper {
    Platform domainMapper;
    PlatformOutputDTO dtoMapper;
    List<PlatformOutputDTO> dtoListMapper;
    List<Platform> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPlatformOutputDTOMapper.INSTANCE.toDTOList(platformDomains);
        Assertions.assertEquals(platformOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        platformDomains = null;
        dtoListMapper = IPlatformOutputDTOMapper.INSTANCE.toDTOList(platformDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPlatformOutputDTOMapper.INSTANCE.toDomainList(platformOutputDTOList);


        Assertions.assertEquals(platformDomains, domainListMapper, MESSAGE_ERROR);

        platformOutputDTOList = null;
        domainListMapper = IPlatformOutputDTOMapper.INSTANCE.toDomainList(platformOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IPlatformOutputDTOMapper.INSTANCE.toDTO(platformDomain);

        Assertions.assertEquals(platformOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(platformOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IPlatformOutputDTOMapper.INSTANCE.toDTO(platformDomain);

        platformDomain = null;
        dtoMapper = IPlatformOutputDTOMapper.INSTANCE.toDTO(platformDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPlatformOutputDTOMapper.INSTANCE.toDomain(platformOutputDTO);

        Assertions.assertEquals(platformDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(platformDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        platformOutputDTO = null;
        domainMapper = IPlatformOutputDTOMapper.INSTANCE.toDomain(platformOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
