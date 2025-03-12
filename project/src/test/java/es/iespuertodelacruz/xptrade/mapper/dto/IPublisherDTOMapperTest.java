package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.dto.PublisherDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPublisherDTOMapperTest extends MapperDTOHelper {
    Publisher domainMapper;
    PublisherDTO dtoMapper;
    List<PublisherDTO> dtoListMapper;
    List<Publisher> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPublisherDTOMapper.INSTANCE.toDTOList(publisherDomains);
        Assertions.assertEquals(publisherDTOList, dtoListMapper, MESSAGE_ERROR);

        publisherDomains = null;
        dtoListMapper = IPublisherDTOMapper.INSTANCE.toDTOList(publisherDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPublisherDTOMapper.INSTANCE.toDomainList(publisherDTOList);


        Assertions.assertEquals(publisherDomains, domainListMapper, MESSAGE_ERROR);

        publisherDTOList = null;
        domainListMapper = IPublisherDTOMapper.INSTANCE.toDomainList(publisherDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IPublisherDTOMapper.INSTANCE.toDTO(publisherDomain);

        Assertions.assertEquals(publisherDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IPublisherDTOMapper.INSTANCE.toDTO(publisherDomain);

        publisherDomain = null;
        dtoMapper = IPublisherDTOMapper.INSTANCE.toDTO(publisherDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPublisherDTOMapper.INSTANCE.toDomain(publisherDTO);

        Assertions.assertEquals(publisherDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        publisherDTO = null;
        domainMapper = IPublisherDTOMapper.INSTANCE.toDomain(publisherDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
