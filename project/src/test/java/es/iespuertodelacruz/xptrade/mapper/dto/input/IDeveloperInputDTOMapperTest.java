package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.dto.input.DeveloperInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IDeveloperInputDTOMapperTest extends MapperInputDTOHelper {
    Developer domainMapper;
    DeveloperInputDTO dtoMapper;
    List<DeveloperInputDTO> dtoListMapper;
    List<Developer> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IDeveloperInputDTOMapper.INSTANCE.toDTOList(developerDomains);
        Assertions.assertEquals(developerInputDTOList, dtoListMapper, MESSAGE_ERROR);

        developerDomains = null;
        dtoListMapper = IDeveloperInputDTOMapper.INSTANCE.toDTOList(developerDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IDeveloperInputDTOMapper.INSTANCE.toDomainList(developerInputDTOList);
        Assertions.assertEquals(developerDomains, domainListMapper, MESSAGE_ERROR);

        developerInputDTOList = null;
        domainListMapper = IDeveloperInputDTOMapper.INSTANCE.toDomainList(developerInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IDeveloperInputDTOMapper.INSTANCE.toDTO(developerDomain);

        Assertions.assertEquals(developerInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IDeveloperInputDTOMapper.INSTANCE.toDTO(developerDomain);

        developerDomain = null;
        dtoMapper = IDeveloperInputDTOMapper.INSTANCE.toDTO(developerDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IDeveloperInputDTOMapper.INSTANCE.toDomain(developerInputDTO);

        Assertions.assertEquals(developerDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(developerDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        developerInputDTO = null;
        domainMapper = IDeveloperInputDTOMapper.INSTANCE.toDomain(developerInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
