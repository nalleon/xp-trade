package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.dto.input.TagInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ITagInputDTOMapperTest extends MapperInputDTOHelper {
    Tag domainMapper;
    TagInputDTO dtoMapper;
    List<TagInputDTO> dtoListMapper;
    List<Tag> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ITagInputDTOMapper.INSTANCE.toDTOList(tagDomains);
        Assertions.assertEquals(tagInputDTOList, dtoListMapper, MESSAGE_ERROR);

        tagDomains = null;
        dtoListMapper = ITagInputDTOMapper.INSTANCE.toDTOList(tagDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ITagInputDTOMapper.INSTANCE.toDomainList(tagInputDTOList);


        Assertions.assertEquals(tagDomains, domainListMapper, MESSAGE_ERROR);

        tagInputDTOList = null;
        domainListMapper = ITagInputDTOMapper.INSTANCE.toDomainList(tagInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void  toDTOTest (){
        dtoMapper = ITagInputDTOMapper.INSTANCE.toDTO(tagDomain);

        Assertions.assertEquals(tagInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = ITagInputDTOMapper.INSTANCE.toDTO(tagDomain);

        tagDomain = null;
        dtoMapper = ITagInputDTOMapper.INSTANCE.toDTO(tagDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest (){
        domainMapper = ITagInputDTOMapper.INSTANCE.toDomain(tagInputDTO);

        Assertions.assertEquals(tagDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        tagInputDTO = null;
        domainMapper = ITagInputDTOMapper.INSTANCE.toDomain(tagInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
