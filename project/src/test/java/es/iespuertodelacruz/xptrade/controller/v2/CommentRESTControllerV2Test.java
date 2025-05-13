package es.iespuertodelacruz.xptrade.controller.v2;

import es.iespuertodelacruz.xptrade.controllers.v2.CommentRESTControllerV2;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.service.*;
import es.iespuertodelacruz.xptrade.dto.input.CommentInputDTO;
import es.iespuertodelacruz.xptrade.dto.input.PostInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.CommentOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.PostOutputDTO;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.model.service.rest.CommentEntityService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
//TODO: actualizar tests de commentcontroller
public class CommentRESTControllerV2Test extends MapperDTOHelper {
    @Mock
    CommentService serviceMock;
    @Mock
    UserService serviceUserMock;
    @Mock
    PostService servicePostMock;
    @Mock
    CommentEntityService entityServiceMock;

    @InjectMocks
    CommentService serviceMockException;

    @InjectMocks
    CommentRESTControllerV2 controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);
        controller.setService(serviceMock);
        serviceMock.setRepository(entityServiceMock);
        controller.setService(serviceMock);
        controller.setPostService(servicePostMock);
        controller.setUserService(serviceUserMock);
        serviceMockException.setRepository(entityServiceMock);
    }
    @Test
    void getAllTest() {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment(1));
        list.add(new Comment(2));
        list.add(new Comment(3));
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }
    
    @Test
    void getAllEmptyTest() {
        when(serviceMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAll().getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllByUserTest() {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment(1));
        list.add(new Comment(2));
        list.add(new Comment(3));
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.findByUser(new User())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByUser("A"), MESSAGE_ERROR);
    }


    @Test
    void getAllByUserNoFilterTest() {
        when(serviceMock.findByUser(new User())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByUser("A"), MESSAGE_ERROR);
    }

    @Test
    void getAllByUserEmptyTest() {
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.findByUser(new User())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByUser("A").getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void getAllByPostTest() {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment(1));
        list.add(new Comment(2));
        list.add(new Comment(3));
        when(servicePostMock.findById(any(Integer.class))).thenReturn(new Post());
        when(serviceMock.findByPost(new Post())).thenReturn(list);
        Assertions.assertNotNull(controller.getAllByPost(1), MESSAGE_ERROR);
    }

    @Test
    void getAllByPostNoFilterTest() {
        when(serviceMock.findByPost(new Post())).thenReturn(null);
        Assertions.assertNotNull(controller.getAllByPost(1), MESSAGE_ERROR);
    }

    @Test
    void getAllByPostEmptyTest() {
        when(servicePostMock.findById(any(Integer.class))).thenReturn(new Post());
        when(serviceMock.findByPost(new Post())).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAllByPost(1).getStatusCode(), MESSAGE_ERROR);
    }

    
    @Test
    void getOneTest() {
        when(serviceMock.findById(1)).thenReturn(new Comment(1));
        Assertions.assertNotNull(controller.getById(1), MESSAGE_ERROR);
    }

    @Test
    void getOneNotFoundTest() {
        when(serviceMock.findById(1)).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.getById(1).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addTest() {
        when(serviceMock.add(any(Post.class), any(User.class), any(String.class))).thenReturn(new Comment());
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART, SLUG,
                        new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                        new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());


        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.add(aux);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(null).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void addPostNullTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART, SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(servicePostMock.findById(anyInt())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void addUserNullTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART, SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.add(aux).getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void addThrowsExceptionTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);

        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(entityServiceMock.save(any(Comment.class))).thenThrow(new RuntimeException());

        controller.setService(serviceMockException);

        ResponseEntity<CustomApiResponse<?>> response = controller.add(aux);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), MESSAGE_ERROR);
    }



    @Test
    void deleteTest() {
        Comment aux = new Comment(postDomain, userDomain, CONTENT);

        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(true);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void deleteErrorTest() {
        Comment aux = new Comment(postDomain, userDomain, CONTENT);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.delete(any(Integer.class))).thenReturn(false);
        ResponseEntity responseEntity = controller.delete(1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(anyInt())).thenReturn(new Comment());
        when(servicePostMock.add(any(Game.class), any(User.class), anyString(), anyString())).thenReturn(new Post());
        when(serviceMock.update(anyInt(), any(Post.class), any(User.class), anyString())).thenReturn(new Comment());

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() {
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateGameNullTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);

        when(serviceMock.findById(anyInt())).thenReturn(new Comment());
        when(servicePostMock.findById(anyInt())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID, aux).getStatusCode(), MESSAGE_ERROR);    }


    @Test
    void updateUserNullTest() {

        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(serviceMock.findById(anyInt())).thenReturn(new Comment());
        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateIdNullTest() {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(serviceMock.findById(anyInt())).thenReturn(new Comment());
        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());
        when(serviceMock.update(anyInt(),any(Post.class), any(User.class), anyString())).thenReturn(null);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, controller.update(ID,aux).getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateExceptionTest() throws Exception {
        CommentInputDTO aux = new CommentInputDTO(
                new PostOutputDTO(ID, new GameOutputDTO(ID, TITLE, COVER_ART,SLUG,
                        new HashSet<>(), new HashSet<>(),
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                        new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE),
                        CONTENT, PICTURE, CREATION_DATE), new UserDTO(ID, USERNAME, EMAIL, PASSWORD,
                new RoleOutputDTO(ID, NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE), CONTENT);


        when(servicePostMock.findById(anyInt())).thenReturn(new Post());
        when(serviceUserMock.findByUsername(anyString())).thenReturn(new User());

        when(serviceMock.findById(any(Integer.class))).thenReturn(new Comment());
        when(serviceMock.update(anyInt(),any(Post.class), any(User.class), anyString())).thenThrow(new RuntimeException());

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, aux);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

}
