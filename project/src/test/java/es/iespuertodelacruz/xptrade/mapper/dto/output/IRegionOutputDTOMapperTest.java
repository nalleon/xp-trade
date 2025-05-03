package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.dto.output.RegionOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRegionOutputDTOMapperTest extends MapperDTOHelper {
    Region domainMapper;
    RegionOutputDTO dtoMapper;
    List<RegionOutputDTO> dtoListMapper;
    List<Region> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IRegionOutputDTOMapper.INSTANCE.toDTOList(regionDomains);
        Assertions.assertEquals(regionOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        regionDomains = null;
        dtoListMapper = IRegionOutputDTOMapper.INSTANCE.toDTOList(regionDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRegionOutputDTOMapper.INSTANCE.toDomainList(regionOutputDTOList);


        Assertions.assertEquals(regionDomains, domainListMapper, MESSAGE_ERROR);

        regionOutputDTOList = null;
        domainListMapper = IRegionOutputDTOMapper.INSTANCE.toDomainList(regionOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IRegionOutputDTOMapper.INSTANCE.toDTO(regionDomain);

        Assertions.assertEquals(regionOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(regionOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IRegionOutputDTOMapper.INSTANCE.toDTO(regionDomain);

        regionDomain = null;
        dtoMapper = IRegionOutputDTOMapper.INSTANCE.toDTO(regionDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IRegionOutputDTOMapper.INSTANCE.toDomain(regionOutputDTO);

        Assertions.assertEquals(regionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(regionDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);

        regionOutputDTO = null;
        domainMapper = IRegionOutputDTOMapper.INSTANCE.toDomain(regionOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
