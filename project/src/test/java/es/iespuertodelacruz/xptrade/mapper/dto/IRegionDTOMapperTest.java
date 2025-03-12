package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.dto.RegionDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRegionDTOMapperTest extends MapperDTOHelper {
    Region domainMapper;
    RegionDTO dtoMapper;
    List<RegionDTO> dtoListMapper;
    List<Region> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IRegionDTOMapper.INSTANCE.toDTOList(regionDomains);
        Assertions.assertEquals(regionDTOList, dtoListMapper, MESSAGE_ERROR);

        regionDomains = null;
        dtoListMapper = IRegionDTOMapper.INSTANCE.toDTOList(regionDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRegionDTOMapper.INSTANCE.toDomainList(regionDTOList);


        Assertions.assertEquals(regionDomains, domainListMapper, MESSAGE_ERROR);

        regionDTOList = null;
        domainListMapper = IRegionDTOMapper.INSTANCE.toDomainList(regionDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IRegionDTOMapper.INSTANCE.toDTO(regionDomain);

        Assertions.assertEquals(regionDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(regionDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IRegionDTOMapper.INSTANCE.toDTO(regionDomain);

        regionDomain = null;
        dtoMapper = IRegionDTOMapper.INSTANCE.toDTO(regionDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IRegionDTOMapper.INSTANCE.toDomain(regionDTO);

        Assertions.assertEquals(regionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(regionDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);

        regionDTO = null;
        domainMapper = IRegionDTOMapper.INSTANCE.toDomain(regionDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
