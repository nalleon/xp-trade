package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.dto.output.PublisherOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPublisherOutputDTOMapper;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPublisherOutputDTOMapperTest extends MapperDTOHelper {
    Publisher domainMapper;
    PublisherOutputDTO dtoMapper;
    List<PublisherOutputDTO> dtoListMapper;
    List<Publisher> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPublisherOutputDTOMapper.INSTANCE.toDTOList(publisherDomains);
        Assertions.assertEquals(publisherOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        publisherDomains = null;
        dtoListMapper = IPublisherOutputDTOMapper.INSTANCE.toDTOList(publisherDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPublisherOutputDTOMapper.INSTANCE.toDomainList(publisherOutputDTOList);


        Assertions.assertEquals(publisherDomains, domainListMapper, MESSAGE_ERROR);

        publisherOutputDTOList = null;
        domainListMapper = IPublisherOutputDTOMapper.INSTANCE.toDomainList(publisherOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IPublisherOutputDTOMapper.INSTANCE.toDTO(publisherDomain);

        Assertions.assertEquals(publisherOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IPublisherOutputDTOMapper.INSTANCE.toDTO(publisherDomain);

        publisherDomain = null;
        dtoMapper = IPublisherOutputDTOMapper.INSTANCE.toDTO(publisherDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPublisherOutputDTOMapper.INSTANCE.toDomain(publisherOutputDTO);

        Assertions.assertEquals(publisherDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        publisherOutputDTO = null;
        domainMapper = IPublisherOutputDTOMapper.INSTANCE.toDomain(publisherOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
