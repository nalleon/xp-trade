package es.iespuertodelacruz.xptrade.shared.utils;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class FileStorageServiceTest extends TestUtilities {

    private FileStorageService fileStorageService;

    @BeforeEach
    void beforeEach() {
        fileStorageService = new FileStorageService();
    }

    @Test
    void saveByteArray_createsFileSuccessfully() {
        String filename = "testfile.txt";
        byte[] data = "Contenido de prueba".getBytes();

        String savedFilename = fileStorageService.save(filename, data);

        Assertions.assertNotNull(savedFilename, MESSAGE_ERROR);
        Assertions.assertTrue(Files.exists(Paths.get("src/main/resources/uploads").resolve(savedFilename)), MESSAGE_ERROR);

        try {
            Files.delete(Paths.get("src/main/resources/uploads").resolve(savedFilename));
        } catch (IOException ignored) {}
    }

    @Test
    void saveMultipartFile_createsFileSuccessfully() {
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                "testfile.txt",
                "text/plain",
                "Contenido de prueba".getBytes()
        );

        String savedFilename = fileStorageService.save(multipartFile);

        Assertions.assertNotNull(savedFilename, MESSAGE_ERROR);
        Assertions.assertTrue(Files.exists(Paths.get("src/main/resources/uploads").resolve(savedFilename)), MESSAGE_ERROR);

        try {
            Files.delete(Paths.get("src/main/resources/uploads").resolve(savedFilename));
        } catch (IOException ignored) {}
    }

    @Test
    void get_existingFile_returnsResource() throws IOException {
        String filename = "testfile_to_get.txt";
        Path path = Paths.get("src/main/resources/uploads").resolve(filename);
        Files.createDirectories(path.getParent());
        Files.write(path, "contenido".getBytes());

        Resource resource = fileStorageService.get(filename);

        Assertions.assertNotNull(resource, MESSAGE_ERROR);
        Assertions.assertTrue(resource.exists(), MESSAGE_ERROR);

        Files.delete(path);
    }

    @Test
    void get_nonExistentFile_throwsException() {
        String filename = "no_existe.txt";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fileStorageService.get(filename);
        });

        Assertions.assertTrue(exception.getMessage().contains("no se puede acceder"), MESSAGE_ERROR);
    }

    @Test
    void saveByteArray_directoryCreationFails_throwsException() {
        FileStorageService serviceSpy = Mockito.spy(fileStorageService);
        Path mockedRoot = Paths.get("/invalid/path/for/test");

        try {
            var rootField = FileStorageService.class.getDeclaredField("root");
            rootField.setAccessible(true);
            rootField.set(serviceSpy, mockedRoot);
        } catch (Exception e) {
            fail("No se pudo cambiar el path root para el test");
        }

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                serviceSpy.save("file.txt", "data".getBytes())
        );

        Assertions.assertEquals("no se puede crear el directorio", exception.getMessage(), MESSAGE_ERROR);
    }

}
