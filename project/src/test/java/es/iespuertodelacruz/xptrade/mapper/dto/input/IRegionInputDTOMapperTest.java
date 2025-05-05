package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.dto.input.RegionInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRegionInputDTOMapperTest extends MapperInputDTOHelper {
    Region domainMapper;
    RegionInputDTO dtoMapper;
    List<RegionInputDTO> dtoListMapper;
    List<Region> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IRegionInputDTOMapper.INSTANCE.toDTOList(regionDomains);
        Assertions.assertEquals(regionInputDTOList, dtoListMapper, MESSAGE_ERROR);

        regionDomains = null;
        dtoListMapper = IRegionInputDTOMapper.INSTANCE.toDTOList(regionDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRegionInputDTOMapper.INSTANCE.toDomainList(regionInputDTOList);


        Assertions.assertEquals(regionDomains, domainListMapper, MESSAGE_ERROR);

        regionInputDTOList = null;
        domainListMapper = IRegionInputDTOMapper.INSTANCE.toDomainList(regionInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IRegionInputDTOMapper.INSTANCE.toDTO(regionDomain);

        Assertions.assertEquals(regionInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IRegionInputDTOMapper.INSTANCE.toDTO(regionDomain);

        regionDomain = null;
        dtoMapper = IRegionInputDTOMapper.INSTANCE.toDTO(regionDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IRegionInputDTOMapper.INSTANCE.toDomain(regionInputDTO);

        Assertions.assertEquals(regionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(regionDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);

        regionInputDTO = null;
        domainMapper = IRegionInputDTOMapper.INSTANCE.toDomain(regionInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
