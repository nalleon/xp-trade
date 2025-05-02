package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.dto.input.PublisherInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPublisherInputDTOMapperTest extends MapperInputDTOHelper {
    Publisher domainMapper;
    PublisherInputDTO dtoMapper;
    List<PublisherInputDTO> dtoListMapper;
    List<Publisher> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPublisherInputDTOMapper.INSTANCE.toDTOList(publisherDomains);
        Assertions.assertEquals(publisherInputDTOList, dtoListMapper, MESSAGE_ERROR);

        publisherDomains = null;
        dtoListMapper = IPublisherInputDTOMapper.INSTANCE.toDTOList(publisherDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPublisherInputDTOMapper.INSTANCE.toDomainList(publisherInputDTOList);


        Assertions.assertEquals(publisherDomains, domainListMapper, MESSAGE_ERROR);

        publisherInputDTOList = null;
        domainListMapper = IPublisherInputDTOMapper.INSTANCE.toDomainList(publisherInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = IPublisherInputDTOMapper.INSTANCE.toDTO(publisherDomain);

        Assertions.assertEquals(publisherInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IPublisherInputDTOMapper.INSTANCE.toDTO(publisherDomain);

        publisherDomain = null;
        dtoMapper = IPublisherInputDTOMapper.INSTANCE.toDTO(publisherDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = IPublisherInputDTOMapper.INSTANCE.toDomain(publisherInputDTO);

        Assertions.assertEquals(publisherDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        publisherInputDTO = null;
        domainMapper = IPublisherInputDTOMapper.INSTANCE.toDomain(publisherInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
