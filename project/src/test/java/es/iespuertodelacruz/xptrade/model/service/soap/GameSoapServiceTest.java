package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import es.iespuertodelacruz.xptrade.model.repository.*;
import es.iespuertodelacruz.xptrade.model.service.rest.GameEntityService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameSoapServiceTest extends TestUtilities {
    @Mock
    IGameEntityRepository repositoryMock;

    @Mock
    GameEntityService serviceMock;

    @Mock
    IDeveloperEntityRepository developerRepositoryMock;
    @Mock
    IPublisherEntityRepository publisherRepositoryMock;
    @Mock
    IGenreEntityRepository genreRepositoryMock;
    @Mock
    IPlatformEntityRepository platformRepositoryMock;
    @Mock
    IRegionEntityRepository regionRepositoryMock;
    @Mock
    ITagEntityRepository tagRepositoryMock;


    @InjectMocks
    GameSoapService service;

    Game item;

    Genre genre;
    Region region;
    Publisher publisher;
    Developer developer;
    Platform platform;
    Tag tag;
    Set<Tag> tagSet;
    Set<Developer> developerSet;
    Set<Genre> genreSet;
    Set<Platform> platformSet;
    Set<Publisher> publisherSet;
    Set<Region> regionSet;

    @BeforeEach
    public void beforeEach() {
        genre = new Genre(NAME);
        region = new Region(NAME);
        publisher = new Publisher(NAME);
        developer = new Developer(NAME);
        platform = new Platform(NAME);
        tag = new Tag(NAME);
        genre.setId(ID);
        region.setId(ID);
        publisher.setId(ID);
        developer.setId(ID);
        platform.setId(ID);
        tag.setId(ID);
        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));
        tagSet = new HashSet<>(Collections.singletonList(tag));

        item =  new Game(TITLE, COVER_ART, SLUG, RATING, RELEASED, tagSet, developerSet, genreSet, platformSet, publisherSet);
        item.setId(ID);

        MockitoAnnotations.openMocks(this);
        serviceMock = new GameEntityService();
        serviceMock.setRepository(repositoryMock);
        serviceMock.setRegionRepository(regionRepositoryMock);
        serviceMock.setPublisherRepository(publisherRepositoryMock);
        serviceMock.setDeveloperRepository(developerRepositoryMock);
        serviceMock.setGenreRepository(genreRepositoryMock);
        serviceMock.setPlatformRepository(platformRepositoryMock);
        serviceMock.setTagRepository(tagRepositoryMock);

        service = new GameSoapService();
        service.setService(serviceMock);


    }
    @Test
    void getAllTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAll()).thenReturn(list);
        Assertions.assertNotNull(service.findAll(), MESSAGE_ERROR);
    }

    @Test
    void getByIdNullTest() {
        Assertions.assertNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByTitleNullTest() {
        Assertions.assertNull(service.findByTitle(TITLE), MESSAGE_ERROR);
    }

    @Test
    void getAllByRegionNullTest() {
        Assertions.assertNull(service.findAllByRegion(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByGenreNullTest() {
        Assertions.assertNull(service.findAllByGenre(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByPlatformNullTest() {
        Assertions.assertNull(service.findAllByPlatform(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByDeveloperNullTest() {
        Assertions.assertNull(service.findAllByDeveloper(null), MESSAGE_ERROR);
    }

    @Test
    void getAllByPublisherNullTest() {
        Assertions.assertNull(service.findAllByPublisher(null), MESSAGE_ERROR);
    }


    @Test
    void getOneTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new GameEntity()));
        Assertions.assertNotNull(service.findById(1), MESSAGE_ERROR);
    }

    @Test
    void getByTitleTest() {
        when(repositoryMock.findByTitle(TITLE)).thenReturn(Optional.of(new GameEntity()));
        Assertions.assertNotNull(service.findByTitle(TITLE), MESSAGE_ERROR);
    }


    @Test
    void getAllByDeveloperTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByDeveloper(developer.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByDeveloper(developer), MESSAGE_ERROR);
    }

    @Test
    void getAllByPublisherTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByPublisher(publisher.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByPublisher(publisher), MESSAGE_ERROR);
    }

    @Test
    void getAllByGenreTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByGenre(genre.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByGenre(genre), MESSAGE_ERROR);
    }

    @Test
    void getAllByRegionTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByRegion(region.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByRegion(region), MESSAGE_ERROR);
    }

    @Test
    void getAllByPlatformTest() {
        List<GameEntity> list = new ArrayList<>();
        list.add(new GameEntity());
        when(repositoryMock.findAllByPlatform(platform.getId())).thenReturn(list);
        Assertions.assertNotNull(service.findAllByPlatform(platform), MESSAGE_ERROR);
    }




    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }


    @Test
    void addExceptionTest() throws Exception {
        when(repositoryMock.save(any(GameEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(new Game(1)), MESSAGE_ERROR);
    }

    @Test
    void updateTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(new GameEntity()));
        Assertions.assertNotNull(service.update(item), MESSAGE_ERROR);
    }

    @Test
    void updateFalseTest() throws Exception {
        Assertions.assertNull(service.update(null), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.empty());
        Assertions.assertNull(service.update(item), MESSAGE_ERROR);
    }


    @Test
    void deleteAdminTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.of(new GameEntity(1)));
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteNotFoundTest() {
        when(repositoryMock.findById(1)).thenReturn(Optional.empty());
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void deleteTest() {
        when(repositoryMock.deleteEntityById(2)).thenReturn(2);
        Assertions.assertTrue(service.delete(2), MESSAGE_ERROR);
    }


    @Test
    void deleteNonExistentTest() {
        when(repositoryMock.deleteEntityById(1)).thenReturn(0);
        Assertions.assertFalse(service.delete(1), MESSAGE_ERROR);
    }

    @Test
    void updateCoverPictureTest() throws Exception {
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(new GameEntity()));

        Assertions.assertNotNull(service.updateCoverArt(item), MESSAGE_ERROR);
    }

    @Test
    void updateNotFoundCoverTest() throws Exception {

        when(repositoryMock.findById(item.getId())).thenReturn(Optional.empty());

        Assertions.assertNull(service.updateCoverArt(item), MESSAGE_ERROR);
    }

    @Test
    void updatePictureFalseTest() throws Exception {
        Assertions.assertNull(service.updateCoverArt(item), MESSAGE_ERROR);
    }

}

