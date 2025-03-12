package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Collection;

import es.iespuertodelacruz.xptrade.dto.CollectionDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICollectionDTOMapperTest extends MapperDTOHelper {
    Collection domainMapper;
    CollectionDTO dtoMapper;
    List<CollectionDTO> dtoListMapper;
    List<Collection> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ICollectionDTOMapper.INSTANCE.toDTOList(collectionDomains);
        Assertions.assertEquals(collectionDTOList, dtoListMapper, MESSAGE_ERROR);

        collectionDomains = null;
        dtoListMapper = ICollectionDTOMapper.INSTANCE.toDTOList(collectionDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICollectionDTOMapper.INSTANCE.toDomainList(collectionDTOList);
        Assertions.assertEquals(collectionDomains, domainListMapper, MESSAGE_ERROR);

        collectionDTOList = null;
        domainListMapper = ICollectionDTOMapper.INSTANCE.toDomainList(collectionDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = ICollectionDTOMapper.INSTANCE.toDTO(collectionDomain);

        Assertions.assertEquals(collectionDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDTO.game(), dtoMapper.game(), MESSAGE_ERROR);

        dtoMapper = ICollectionDTOMapper.INSTANCE.toDTO(collectionDomain);

        collectionDomain = null;
        dtoMapper = ICollectionDTOMapper.INSTANCE.toDTO(collectionDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICollectionDTOMapper.INSTANCE.toDomain(collectionDTO);

        Assertions.assertEquals(collectionDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);

        collectionDTO = null;
        domainMapper = ICollectionDTOMapper.INSTANCE.toDomain(collectionDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
