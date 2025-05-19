package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Collection;

import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CollectionOutputDTOTest;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ICollectionOutputDTOMapperTest extends MapperDTOHelper {
    Collection domainMapper;
    CollectionOutputDTO dtoMapper;
    List<CollectionOutputDTO> dtoListMapper;
    List<Collection> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ICollectionOutputDTOMapper.INSTANCE.toDTOList(collectionDomains);
        dtoListMapper = dtoListMapper.stream().map(collection ->
            new CollectionOutputDTO(collection.id(), gameCollectionOutputDTOList, collection.user())
        ).toList();
        Assertions.assertEquals(collectionOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        collectionDomains = null;
        dtoListMapper = ICollectionOutputDTOMapper.INSTANCE.toDTOList(collectionDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICollectionOutputDTOMapper.INSTANCE.toDomainList(collectionOutputDTOList);
        Assertions.assertEquals(collectionDomains, domainListMapper, MESSAGE_ERROR);

        collectionOutputDTOList = null;
        domainListMapper = ICollectionOutputDTOMapper.INSTANCE.toDomainList(collectionOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = ICollectionOutputDTOMapper.INSTANCE.toDTO(collectionDomain);

        Assertions.assertEquals(collectionOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionOutputDTO.user(), dtoMapper.user(), MESSAGE_ERROR);

        dtoMapper = ICollectionOutputDTOMapper.INSTANCE.toDTO(collectionDomain);

        collectionDomain = null;
        dtoMapper = ICollectionOutputDTOMapper.INSTANCE.toDTO(collectionDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICollectionOutputDTOMapper.INSTANCE.toDomain(collectionOutputDTO);

        Assertions.assertEquals(collectionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);

        collectionOutputDTO = null;
        domainMapper = ICollectionOutputDTOMapper.INSTANCE.toDomain(collectionOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
