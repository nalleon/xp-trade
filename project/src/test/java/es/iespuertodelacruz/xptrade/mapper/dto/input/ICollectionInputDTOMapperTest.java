package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.input.CollectionInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICollectionInputDTOMapperTest extends MapperInputDTOHelper {
    Collection domainMapper;
    CollectionInputDTO dtoMapper;
    List<CollectionInputDTO> dtoListMapper;
    List<Collection> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ICollectionInputDTOMapper.INSTANCE.toDTOList(collectionDomains);

        Assertions.assertNotNull(dtoListMapper, MESSAGE_ERROR);
        Assertions.assertEquals(collectionInputDTOList.get(0).user().username(), dtoListMapper.get(0).user().username(), MESSAGE_ERROR);


        collectionDomains = null;
        dtoListMapper = ICollectionInputDTOMapper.INSTANCE.toDTOList(collectionDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICollectionInputDTOMapper.INSTANCE.toDomainList(collectionInputDTOList);
        Assertions.assertEquals(collectionDomains, domainListMapper, MESSAGE_ERROR);

        collectionInputDTOList = null;
        domainListMapper = ICollectionInputDTOMapper.INSTANCE.toDomainList(collectionInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = ICollectionInputDTOMapper.INSTANCE.toDTO(collectionDomain);

        Assertions.assertEquals(collectionInputDTO.user().username(), dtoMapper.user().username(), MESSAGE_ERROR);

        dtoMapper = ICollectionInputDTOMapper.INSTANCE.toDTO(collectionDomain);

        collectionDomain = null;
        dtoMapper = ICollectionInputDTOMapper.INSTANCE.toDTO(collectionDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICollectionInputDTOMapper.INSTANCE.toDomain(collectionInputDTO);

        Assertions.assertEquals(collectionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain.getUser().getUsername(), domainMapper.getUser().getUsername(), MESSAGE_ERROR);

        collectionInputDTO = null;
        domainMapper = ICollectionInputDTOMapper.INSTANCE.toDomain(collectionInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
