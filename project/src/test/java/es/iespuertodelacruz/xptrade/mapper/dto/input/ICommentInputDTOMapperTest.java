package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.dto.input.CommentInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ICommentInputDTOMapperTest extends MapperInputDTOHelper {
    Comment domainMapper;
    CommentInputDTO dtoMapper;
    List<CommentInputDTO> dtoListMapper;
    List<Comment> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = ICommentInputDTOMapper.INSTANCE.toDTOList(commentDomains);

        Assertions.assertNotNull(dtoListMapper, MESSAGE_ERROR);

        Assertions.assertEquals(commentInputDTOList.get(0).user().username(), dtoListMapper.get(0).user().username(), MESSAGE_ERROR);
        Assertions.assertEquals(commentInputDTOList.get(0).post().game().title(), dtoListMapper.get(0).post().game().title(), MESSAGE_ERROR);

        commentDomains = null;
        dtoListMapper = ICommentInputDTOMapper.INSTANCE.toDTOList(commentDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = ICommentInputDTOMapper.INSTANCE.toDomainList(commentInputDTOList);
        Assertions.assertEquals(commentDomains, domainListMapper, MESSAGE_ERROR);




        commentInputDTOList = null;
        domainListMapper = ICommentInputDTOMapper.INSTANCE.toDomainList(commentInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
    @Test
    public void toDTOTest(){
        dtoMapper = ICommentInputDTOMapper.INSTANCE.toDTO(commentDomain);

        Assertions.assertEquals(commentInputDTO.user().username(), dtoMapper.user().username(), MESSAGE_ERROR);
        Assertions.assertEquals(commentInputDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(commentInputDTO.content(), dtoMapper.content(), MESSAGE_ERROR);
        Assertions.assertEquals(commentInputDTO.post().game().title(), dtoMapper.post().game().title(), MESSAGE_ERROR);
        Assertions.assertEquals(commentInputDTO.post().user().username(), dtoMapper.post().user().username(), MESSAGE_ERROR);

        dtoMapper = ICommentInputDTOMapper.INSTANCE.toDTO(commentDomain);

        commentDomain.setPost(null);
        dtoMapper = ICommentInputDTOMapper.INSTANCE.toDTO(commentDomain);
        Assertions.assertNull(dtoMapper.post(), MESSAGE_ERROR);

        commentDomain = null;
        dtoMapper = ICommentInputDTOMapper.INSTANCE.toDTO(commentDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = ICommentInputDTOMapper.INSTANCE.toDomain(commentInputDTO);

        Assertions.assertEquals(commentDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getUser(), domainMapper.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getContent(), domainMapper.getContent(), MESSAGE_ERROR);
        Assertions.assertNull(domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getPost().getGame().getTitle(), domainMapper.getPost().getGame().getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(commentDomain.getPost().getUser().getUsername(), domainMapper.getPost().getUser().getUsername(), MESSAGE_ERROR);


        commentInputDTO = new CommentInputDTO(null, userDTO, CONTENT);
        domainMapper = ICommentInputDTOMapper.INSTANCE.toDomain(commentInputDTO);
        Assertions.assertNull(domainMapper.getPost(), MESSAGE_ERROR);

        commentInputDTO = null;
        domainMapper = ICommentInputDTOMapper.INSTANCE.toDomain(commentInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }
}
