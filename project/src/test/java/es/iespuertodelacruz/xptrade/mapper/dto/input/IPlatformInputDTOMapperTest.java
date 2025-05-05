package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.dto.input.PlatformInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPlatformInputDTOMapperTest extends MapperInputDTOHelper {
    Platform domainMapper;
    PlatformInputDTO dtoMapper;
    List<PlatformInputDTO> dtoListMapper;
    List<Platform> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPlatformInputDTOMapper.INSTANCE.toDTOList(platformDomains);
        Assertions.assertEquals(platformInputDTOList, dtoListMapper, MESSAGE_ERROR);

        platformDomains = null;
        dtoListMapper = IPlatformInputDTOMapper.INSTANCE.toDTOList(platformDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPlatformInputDTOMapper.INSTANCE.toDomainList(platformInputDTOList);


        Assertions.assertEquals(platformDomains, domainListMapper, MESSAGE_ERROR);

        platformInputDTOList = null;
        domainListMapper = IPlatformInputDTOMapper.INSTANCE.toDomainList(platformInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IPlatformInputDTOMapper.INSTANCE.toDTO(platformDomain);

        Assertions.assertEquals(platformInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IPlatformInputDTOMapper.INSTANCE.toDTO(platformDomain);

        platformDomain = null;
        dtoMapper = IPlatformInputDTOMapper.INSTANCE.toDTO(platformDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPlatformInputDTOMapper.INSTANCE.toDomain(platformInputDTO);

        Assertions.assertEquals(platformDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(platformDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        platformInputDTO = null;
        domainMapper = IPlatformInputDTOMapper.INSTANCE.toDomain(platformInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
