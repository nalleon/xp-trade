package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.dto.PostDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPostDTOMapperTest extends MapperDTOHelper {
    Post domainMapper;
    PostDTO dtoMapper;
    List<PostDTO> dtoListMapper;
    List<Post> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPostDTOMapper.INSTANCE.toDTOList(postDomains);
        Assertions.assertEquals(postDTOList, dtoListMapper, MESSAGE_ERROR);

        postDomains = null;
        dtoListMapper = IPostDTOMapper.INSTANCE.toDTOList(postDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPostDTOMapper.INSTANCE.toDomainList(postDTOList);
        Assertions.assertEquals(postDomains, domainListMapper, MESSAGE_ERROR);

        postDTOList = null;
        domainListMapper = IPostDTOMapper.INSTANCE.toDomainList(postDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IPostDTOMapper.INSTANCE.toDTO(postDomain);

        Assertions.assertEquals(postDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(postDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(postDTO.game(), dtoMapper.game(), MESSAGE_ERROR);
        Assertions.assertEquals(postDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(postDTO.creationDate(), dtoMapper.creationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postDTO.picture(), dtoMapper.picture(), MESSAGE_ERROR);

        postDomain = null;
        dtoMapper = IPostDTOMapper.INSTANCE.toDTO(postDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IPostDTOMapper.INSTANCE.toDomain(postDTO);

        Assertions.assertEquals(postDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getPicture(), domainMapper.getPicture(), MESSAGE_ERROR);
        postDTO = null;
        domainMapper = IPostDTOMapper.INSTANCE.toDomain(postDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
