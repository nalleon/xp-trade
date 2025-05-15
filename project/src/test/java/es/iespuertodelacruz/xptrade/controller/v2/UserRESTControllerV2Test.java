package es.iespuertodelacruz.xptrade.controller.v2;

import es.iespuertodelacruz.xptrade.controllers.v2.UserRESTControllerV2;
import es.iespuertodelacruz.xptrade.controllers.v3.UserRESTController;
import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.service.UserService;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserUpdateInputDTO;
import es.iespuertodelacruz.xptrade.mapper.entity.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.model.service.rest.UserEntityService;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserRESTControllerV2Test extends TestUtilities {
    @Mock
    UserService serviceMock;
    @Mock
    PasswordEncoder passwordEncoderMock;
    @Mock
    FileStorageService storageServiceMock;

    @Mock
    UserEntityService entityServiceMock;

    @Mock
    IUserEntityRepository repositoryMock;
    @Mock
    IRoleEntityRepository repositoryRoleMock;

    @Mock
    IUserEntityMapper userEntityMapperMock;

    @InjectMocks
    UserService serviceMockException;

    @InjectMocks
    UserRESTControllerV2 controller;


    @BeforeEach
    public void beforeEach (){
        MockitoAnnotations.openMocks(this);

        entityServiceMock.setRepository(repositoryMock);
        entityServiceMock.setRoleRepository(repositoryRoleMock);
        serviceMock.setRepository(entityServiceMock);

        controller.setService(serviceMock);
        controller.setPasswordEncoder(passwordEncoderMock);
        controller.setStorageService(storageServiceMock);
    }
    @Test
    void getAllTest() {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        when(serviceMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(controller.getAll(), MESSAGE_ERROR);
    }


    @Test
    void getAllEmptyTest() {
        when(serviceMock.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getAll().getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void getOneByNameTest() {
        when(serviceMock.findByUsername(anyString())).thenReturn(new User(1));
        Assertions.assertNotNull(controller.getByUsername("a"), MESSAGE_ERROR);
    }

    @Test
    void getOneByNameNotFoundTest() {
        when(serviceMock.findByUsername(anyString())).thenReturn(null);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, controller.getByUsername("a").getStatusCode(), MESSAGE_ERROR);
    }

    @Test
    void updateNullTest() {
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }


    @Test
    void updateNotFoundTest() {
        when(serviceMock.findById(anyInt())).thenReturn(null);

        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD) );
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }

    //@Test
    void updateTest() {
        User existingUser = new User(ID);
        existingUser.setEmail(EMAIL);
        existingUser.setUsername(USERNAME);
        existingUser.setRole(new Role(1, "A"));
        existingUser.setVerified(1);
        existingUser.setProfilePicture("aa");
        existingUser.setPassword("AAAA");
        existingUser.setCreationDate(new Date());
        existingUser.setVerificationToken("AAAAAAAAAAAAAAAA");

        when(repositoryMock.findUserByName(NAME)).thenReturn(Optional.of(IUserEntityMapper.INSTANCE.toEntity(existingUser)));
        when(serviceMock.findById(1)).thenReturn(existingUser);
        when(serviceMock.update(NAME, EMAIL, PASSWORD)).thenReturn(existingUser);
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), MESSAGE_ERROR);
    }
    @Test
    void updateExceptionTest() throws Exception {
        User aux = new User(1);
        when(serviceMock.findById(any(Integer.class))).thenReturn(aux);
        when(serviceMock.update(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Database error"));
        ResponseEntity<CustomApiResponse<?>> responseEntity = controller.update(1, new UserUpdateInputDTO(EMAIL, PASSWORD));

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, responseEntity.getStatusCode(), MESSAGE_ERROR);

    }

    @Test
    void uploadFileSuccessTest() throws Exception {
        String username = "testUser";
        MultipartFile file = mock(MultipartFile.class);
        String filename = "profile_pic.png";

        User user = new User("testUser", "test@domain.com", "password");

        when(storageServiceMock.save(file)).thenReturn(filename);
        when(serviceMock.findByUsername(username)).thenReturn(user);
        when(serviceMock.updatePicture(user.getUsername(), user.getEmail(), user.getPassword(), filename)).thenReturn(user);

        ResponseEntity<?> response = controller.uploadFile(username, file);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), MESSAGE_ERROR);
        Assertions.assertNotNull(response.getBody());

        CustomApiResponse<?> apiResponse = (CustomApiResponse<?>) response.getBody();
        Assertions.assertEquals(200, apiResponse.getStatus(), MESSAGE_ERROR);
    }
    @Test
    void uploadFileFailure() throws Exception {
        String username = "testUser";
        MultipartFile file = mock(MultipartFile.class);

        when(storageServiceMock.save(file)).thenThrow(new RuntimeException("Storage error"));

        ResponseEntity<?> response = controller.uploadFile(username, file);

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode(), MESSAGE_ERROR);
        CustomApiResponse<?> apiResponse = (CustomApiResponse<?>) response.getBody();
        Assertions.assertEquals(417, apiResponse.getStatus(), MESSAGE_ERROR);
        Assertions.assertTrue(apiResponse.getMessage().contains("Could not upload the file"), MESSAGE_ERROR);
    }

    @Test
    void getFilesSuccess() throws Exception {
        String filename = "profile_pic.png";
        Resource resource = mock(Resource.class);
        InputStream inputStream = mock(InputStream.class);

        when(storageServiceMock.get(filename)).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);

        ResponseEntity<?> response = controller.getFiles(filename);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), MESSAGE_ERROR);
        Assertions.assertNotNull(response.getBody(), MESSAGE_ERROR);
        Assertions.assertEquals("application/octet-stream", response.getHeaders().getContentType().toString(), MESSAGE_ERROR);
    }

    @Test
    void getFilesFailureNoContentType() throws Exception {
        String filename = "profile_pic.png";
        Resource resource = mock(Resource.class);
        InputStream inputStream = mock(InputStream.class);

        when(storageServiceMock.get(filename)).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);

        ResponseEntity<?> response = controller.getFiles(filename);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), MESSAGE_ERROR);
        Assertions.assertNotNull(response.getBody(), MESSAGE_ERROR);
        Assertions.assertEquals("application/octet-stream", response.getHeaders().getContentType().toString(), MESSAGE_ERROR);
    }

    @Test
    void getFileExceptionTest() throws IOException {
        Resource resource = mock(Resource.class);
        when(resource.getInputStream()).thenThrow(new IOException());
        when(resource.getFilename()).thenReturn("dummy.txt");
        when(storageServiceMock.get(anyString())).thenReturn(resource);

        Assertions.assertEquals(HttpStatus.EXPECTATION_FAILED, controller.getFiles("dummy.txt").getStatusCode(), MESSAGE_ERROR);
    }



}