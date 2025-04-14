package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.dto.output.DeveloperOutputDTO;

import es.iespuertodelacruz.xptrade.mapper.dto.output.IDeveloperOutputDTOMapper;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IDeveloperOutputDTOMapperTest extends MapperDTOHelper {
    Developer domainMapper;
    DeveloperOutputDTO dtoMapper;
    List<DeveloperOutputDTO> dtoListMapper;
    List<Developer> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IDeveloperOutputDTOMapper.INSTANCE.toDTOList(developerDomains);
        Assertions.assertEquals(developerOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        developerDomains = null;
        dtoListMapper = IDeveloperOutputDTOMapper.INSTANCE.toDTOList(developerDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IDeveloperOutputDTOMapper.INSTANCE.toDomainList(developerOutputDTOList);
        Assertions.assertEquals(developerDomains, domainListMapper, MESSAGE_ERROR);

        developerOutputDTOList = null;
        domainListMapper = IDeveloperOutputDTOMapper.INSTANCE.toDomainList(developerOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IDeveloperOutputDTOMapper.INSTANCE.toDTO(developerDomain);

        Assertions.assertEquals(developerOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(developerOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IDeveloperOutputDTOMapper.INSTANCE.toDTO(developerDomain);

        developerDomain = null;
        dtoMapper = IDeveloperOutputDTOMapper.INSTANCE.toDTO(developerDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IDeveloperOutputDTOMapper.INSTANCE.toDomain(developerOutputDTO);

        Assertions.assertEquals(developerDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(developerDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        developerOutputDTO = null;
        domainMapper = IDeveloperOutputDTOMapper.INSTANCE.toDomain(developerOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
