package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.dto.input.PostInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPostInputDTOMapperTest extends MapperInputDTOHelper {
    Post domainMapper;
    PostInputDTO dtoMapper;
    List<PostInputDTO> dtoListMapper;
    List<Post> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPostInputDTOMapper.INSTANCE.toDTOList(postDomains);
        Assertions.assertNotNull(dtoListMapper, MESSAGE_ERROR);
        Assertions.assertEquals(postInputDTOList.get(0).game().title(), dtoListMapper.get(0).game().title(), MESSAGE_ERROR);

        postDomains = null;
        dtoListMapper = IPostInputDTOMapper.INSTANCE.toDTOList(postDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPostInputDTOMapper.INSTANCE.toDomainList(postInputDTOList);
        Assertions.assertEquals(postDomains, domainListMapper, MESSAGE_ERROR);

        postInputDTOList = null;
        domainListMapper = IPostInputDTOMapper.INSTANCE.toDomainList(postInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IPostInputDTOMapper.INSTANCE.toDTO(postDomain);

        Assertions.assertEquals(postInputDTO.user().username(), dtoMapper.user().username(), MESSAGE_ERROR);
        Assertions.assertEquals(postInputDTO.game().title(), dtoMapper.game().title(), MESSAGE_ERROR);
        Assertions.assertEquals(postInputDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(postInputDTO.picture(), dtoMapper.picture(), MESSAGE_ERROR);

        postDomain = null;
        dtoMapper = IPostInputDTOMapper.INSTANCE.toDTO(postDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IPostInputDTOMapper.INSTANCE.toDomain(postInputDTO);

        Assertions.assertEquals(postDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getUser().getUsername(), domainMapper.getUser().getUsername(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getGame().getTitle(), domainMapper.getGame().getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getPicture(), domainMapper.getPicture(), MESSAGE_ERROR);
        postInputDTO = null;
        domainMapper = IPostInputDTOMapper.INSTANCE.toDomain(postInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
