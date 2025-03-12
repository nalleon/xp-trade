package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Comment;

import es.iespuertodelacruz.xptrade.dto.CommentDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICommentDTOMapperTest extends MapperDTOHelper {
    Comment domainMapper;
    CommentDTO dtoMapper;
    List<CommentDTO> dtoListMapper;
    List<Comment> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ICommentDTOMapper.INSTANCE.toDTOList(commentDomains);
        Assertions.assertEquals(commentDTOList, dtoListMapper, MESSAGE_ERROR);

        commentDomains = null;
        dtoListMapper = ICommentDTOMapper.INSTANCE.toDTOList(commentDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICommentDTOMapper.INSTANCE.toDomainList(commentDTOList);
        Assertions.assertEquals(commentDomains, domainListMapper, MESSAGE_ERROR);




        commentDTOList = null;
        domainListMapper = ICommentDTOMapper.INSTANCE.toDomainList(commentDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = ICommentDTOMapper.INSTANCE.toDTO(commentDomain);


        Assertions.assertEquals(commentDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDTO.post(), dtoMapper.post(), MESSAGE_ERROR);

        dtoMapper = ICommentDTOMapper.INSTANCE.toDTO(commentDomain);

        commentDomain.setPost(null);
        dtoMapper = ICommentDTOMapper.INSTANCE.toDTO(commentDomain);
        Assertions.assertNull(dtoMapper.post(), MESSAGE_ERROR);

        commentDomain = null;
        dtoMapper = ICommentDTOMapper.INSTANCE.toDTO(commentDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICommentDTOMapper.INSTANCE.toDomain(commentDTO);

        Assertions.assertEquals(commentDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getPost(), domainMapper.getPost(), MESSAGE_ERROR);

        commentDTO = new CommentDTO(ID, null, userDTO, CONTENT, CREATION_DATE);
        domainMapper = ICommentDTOMapper.INSTANCE.toDomain(commentDTO);
        Assertions.assertNull(domainMapper.getPost(), MESSAGE_ERROR);

        commentDTO = null;
        domainMapper = ICommentDTOMapper.INSTANCE.toDomain(commentDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
