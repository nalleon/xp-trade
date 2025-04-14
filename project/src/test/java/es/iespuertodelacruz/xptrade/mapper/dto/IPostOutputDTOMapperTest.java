package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IPostOutputDTOMapper;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IPostOutputDTOMapperTest extends MapperDTOHelper {
    Post domainMapper;
    PostOutputDTO dtoMapper;
    List<PostOutputDTO> dtoListMapper;
    List<Post> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IPostOutputDTOMapper.INSTANCE.toDTOList(postDomains);
        Assertions.assertEquals(postOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        postDomains = null;
        dtoListMapper = IPostOutputDTOMapper.INSTANCE.toDTOList(postDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IPostOutputDTOMapper.INSTANCE.toDomainList(postOutputDTOList);
        Assertions.assertEquals(postDomains, domainListMapper, MESSAGE_ERROR);

        postOutputDTOList = null;
        domainListMapper = IPostOutputDTOMapper.INSTANCE.toDomainList(postOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = IPostOutputDTOMapper.INSTANCE.toDTO(postDomain);

        Assertions.assertEquals(postOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(postOutputDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(postOutputDTO.game(), dtoMapper.game(), MESSAGE_ERROR);
        Assertions.assertEquals(postOutputDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(postOutputDTO.creationDate(), dtoMapper.creationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postOutputDTO.picture(), dtoMapper.picture(), MESSAGE_ERROR);

        postDomain = null;
        dtoMapper = IPostOutputDTOMapper.INSTANCE.toDTO(postDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IPostOutputDTOMapper.INSTANCE.toDomain(postOutputDTO);

        Assertions.assertEquals(postDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getGame(), domainMapper.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain.getPicture(), domainMapper.getPicture(), MESSAGE_ERROR);
        postOutputDTO = null;
        domainMapper = IPostOutputDTOMapper.INSTANCE.toDomain(postOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
