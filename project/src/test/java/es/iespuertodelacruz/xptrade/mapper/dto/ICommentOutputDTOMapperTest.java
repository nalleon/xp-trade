package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Comment;

import es.iespuertodelacruz.xptrade.dto.output.CommentOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.ICommentOutputDTOMapper;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICommentOutputDTOMapperTest extends MapperDTOHelper {
    Comment domainMapper;
    CommentOutputDTO dtoMapper;
    List<CommentOutputDTO> dtoListMapper;
    List<Comment> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ICommentOutputDTOMapper.INSTANCE.toDTOList(commentDomains);
        Assertions.assertEquals(commentOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        commentDomains = null;
        dtoListMapper = ICommentOutputDTOMapper.INSTANCE.toDTOList(commentDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICommentOutputDTOMapper.INSTANCE.toDomainList(commentOutputDTOList);
        Assertions.assertEquals(commentDomains, domainListMapper, MESSAGE_ERROR);




        commentOutputDTOList = null;
        domainListMapper = ICommentOutputDTOMapper.INSTANCE.toDomainList(commentOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = ICommentOutputDTOMapper.INSTANCE.toDTO(commentDomain);


        Assertions.assertEquals(commentOutputDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(commentOutputDTO.user(), dtoMapper.user(), MESSAGE_ERROR);
        Assertions.assertEquals(commentOutputDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(commentOutputDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(commentOutputDTO.post(), dtoMapper.post(), MESSAGE_ERROR);

        dtoMapper = ICommentOutputDTOMapper.INSTANCE.toDTO(commentDomain);

        commentDomain.setPost(null);
        dtoMapper = ICommentOutputDTOMapper.INSTANCE.toDTO(commentDomain);
        Assertions.assertNull(dtoMapper.post(), MESSAGE_ERROR);

        commentDomain = null;
        dtoMapper = ICommentOutputDTOMapper.INSTANCE.toDTO(commentDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICommentOutputDTOMapper.INSTANCE.toDomain(commentOutputDTO);

        Assertions.assertEquals(commentDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getPost(), domainMapper.getPost(), MESSAGE_ERROR);

        commentOutputDTO = new CommentOutputDTO(ID, null, userDTO, CONTENT, CREATION_DATE);
        domainMapper = ICommentOutputDTOMapper.INSTANCE.toDomain(commentOutputDTO);
        Assertions.assertNull(domainMapper.getPost(), MESSAGE_ERROR);

        commentOutputDTO = null;
        domainMapper = ICommentOutputDTOMapper.INSTANCE.toDomain(commentOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
