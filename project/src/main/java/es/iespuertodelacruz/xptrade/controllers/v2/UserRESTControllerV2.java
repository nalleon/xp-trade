package es.iespuertodelacruz.xptrade.controllers.v2;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserUpdateInputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import es.iespuertodelacruz.xptrade.shared.utils.CustomApiResponse;
import es.iespuertodelacruz.xptrade.shared.utils.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/users")
@Tag(name="v2 - User", description = "For authenticated users")
public class UserRESTControllerV2 {


    /**
     * Properties
     */
    private IUserService service;

    private FileStorageService storageService;

    private PasswordEncoder passwordEncoder;

    /**
     * Setters of the user service
     * @param service of the user
     */
    @Autowired
    public void setService(IUserService service) {
        this.service = service;
    }

    /**
     * Setters of the user service
     * @param storageService of the user
     */
    @Autowired
    public void setStorageService(FileStorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Setters of the user service
     * @param passwordEncoder of the role
     */
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDTO> filteredList = IUserDTOMapper.INSTANCE.toDTOList(service.findAll());
        if (filteredList.isEmpty()) {
            String message = "There are no users";
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, message, filteredList));
        }

        String message = "List successfully obtained";
        return ResponseEntity.ok(new CustomApiResponse<>(200, message, filteredList));
    }

    @GetMapping("username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        User aux = service.findByUsername(username);
        if (aux != null){
            UserDTO dto = IUserDTOMapper.INSTANCE.toDTO(aux);

            CustomApiResponse<UserDTO> response = new CustomApiResponse<>(200, "User found", dto);
            return ResponseEntity.ok(response);
        }

        CustomApiResponse<UserOutputDTO> errorResponse = new CustomApiResponse<>(204, "User NOT found", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> update(
            @PathVariable Integer id,
            @RequestBody UserUpdateInputDTO updateInputDTO) {

        if (updateInputDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomApiResponse<>(400, "User cannot be null", null));
        }

        User dbItem = service.findById(id);

        if (dbItem == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new CustomApiResponse<>(204, "User NOT found", null));
        }

        try {

            dbItem.setPassword(passwordEncoder.encode(updateInputDTO.password()));
            dbItem.setEmail(updateInputDTO.email());

            User updatedDbItem = service.update(dbItem.getUsername(), dbItem.getEmail(), dbItem.getPassword());

            UserOutputDTO result = new UserOutputDTO(updatedDbItem.getUsername(), updatedDbItem.getEmail());

            return ResponseEntity.ok(new CustomApiResponse<>(200, "Update successful", result));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CustomApiResponse<>(417, "Error while trying to update", null));
        }
    }

    @PostMapping(value = "/uploads/{username}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("username") String username, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String namefile = storageService.save(file);
            message = "" + namefile;

            User aux = service.findByUsername(username);

            aux.setProfilePicture(namefile);

            User result = service.updatePicture(aux.getUsername(), aux.getEmail(), aux.getPassword(), aux.getProfilePicture());

            UserDTO dto = IUserDTOMapper.INSTANCE.toDTO(result);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse<>(200, message, dto));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename()
                    + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CustomApiResponse<>(417, message, null));
        }
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<?> getFiles(@PathVariable String filename) {
        Resource resource = storageService.get(filename);

        String contentType = null;
        try {
            contentType = URLConnection.guessContentTypeFromStream(resource.getInputStream());
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                    new CustomApiResponse<>(417, "Could not determine file type.", null));
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        String headerValue = "attachment";
        filename="" +
                resource.getFilename() + "";
        return ResponseEntity.
                ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        org.springframework.http.HttpHeaders.
                                CONTENT_DISPOSITION,
                        headerValue
                )
                .body(resource);
    }
}