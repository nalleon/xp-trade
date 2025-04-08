package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.dto.output.DeveloperDTO;

import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IDeveloperDTOMapperTest extends MapperDTOHelper {
    Developer domainMapper;
    DeveloperDTO dtoMapper;
    List<DeveloperDTO> dtoListMapper;
    List<Developer> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IDeveloperDTOMapper.INSTANCE.toDTOList(developerDomains);
        Assertions.assertEquals(developerDTOList, dtoListMapper, MESSAGE_ERROR);

        developerDomains = null;
        dtoListMapper = IDeveloperDTOMapper.INSTANCE.toDTOList(developerDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IDeveloperDTOMapper.INSTANCE.toDomainList(developerDTOList);
        Assertions.assertEquals(developerDomains, domainListMapper, MESSAGE_ERROR);

        developerDTOList = null;
        domainListMapper = IDeveloperDTOMapper.INSTANCE.toDomainList(developerDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IDeveloperDTOMapper.INSTANCE.toDTO(developerDomain);

        Assertions.assertEquals(developerDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(developerDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IDeveloperDTOMapper.INSTANCE.toDTO(developerDomain);

        developerDomain = null;
        dtoMapper = IDeveloperDTOMapper.INSTANCE.toDTO(developerDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IDeveloperDTOMapper.INSTANCE.toDomain(developerDTO);

        Assertions.assertEquals(developerDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(developerDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        developerDTO = null;
        domainMapper = IDeveloperDTOMapper.INSTANCE.toDomain(developerDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
