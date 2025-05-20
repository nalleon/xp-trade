package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.dto.output.TagOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ITagOutputDTOMapperTest extends MapperDTOHelper {
    Tag domainMapper;
    TagOutputDTO dtoMapper;
    List<TagOutputDTO> dtoListMapper;
    List<Tag> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ITagOutputDTOMapper.INSTANCE.toDTOList(tagDomains);
        Assertions.assertEquals(tagOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        tagDomains = null;
        dtoListMapper = ITagOutputDTOMapper.INSTANCE.toDTOList(tagDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ITagOutputDTOMapper.INSTANCE.toDomainList(tagOutputDTOList);


        Assertions.assertEquals(tagDomains, domainListMapper, MESSAGE_ERROR);

        tagOutputDTOList = null;
        domainListMapper = ITagOutputDTOMapper.INSTANCE.toDomainList(tagOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = ITagOutputDTOMapper.INSTANCE.toDTO(tagDomain);

        Assertions.assertEquals(tagOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(tagOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = ITagOutputDTOMapper.INSTANCE.toDTO(tagDomain);

        tagDomain = null;
        dtoMapper = ITagOutputDTOMapper.INSTANCE.toDTO(tagDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = ITagOutputDTOMapper.INSTANCE.toDomain(tagOutputDTO);

        Assertions.assertEquals(tagDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(tagDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        tagOutputDTO = null;
        domainMapper = ITagOutputDTOMapper.INSTANCE.toDomain(tagOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
