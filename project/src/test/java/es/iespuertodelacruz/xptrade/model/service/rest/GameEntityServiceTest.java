package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.mapper.entity.IGameEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.*;
import es.iespuertodelacruz.xptrade.model.repository.*;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GameEntityServiceTest extends TestUtilities {
    @Mock
    IGameEntityRepository repositoryMock;
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



    @InjectMocks
    GameEntityService service;


    Game item;
    GameEntity entity;

    Genre genre;
    Region region;
    Publisher publisher;
    Developer developer;
    Platform platform;
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

        genre.setId(ID);
        region.setId(ID);
        publisher.setId(ID);
        developer.setId(ID);
        platform.setId(ID);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));

        item =  new Game(TITLE, COVER_ART, developerSet, genreSet, platformSet, publisherSet, regionSet);
        item.setId(ID);

        entity = IGameEntityMapper.INSTANCE.toEntity(item);


        MockitoAnnotations.openMocks(this);
        service = new GameEntityService();
        service.setRepository(repositoryMock);
        service.setDeveloperRepository(developerRepositoryMock);
        service.setGenreRepository(genreRepositoryMock);
        service.setPlatformRepository(platformRepositoryMock);
        service.setPublisherRepository(publisherRepositoryMock);
        service.setRegionRepository(regionRepositoryMock);



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
    void addTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        when(repositoryMock.save(any(GameEntity.class))).thenReturn(new GameEntity());

        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }
    @Test
    void addNullRegionsTest() {
        item.setRegionSet(new HashSet<>());
        Assertions.assertNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullPlatformsTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        item.setPlatformSet(new HashSet<>());
        Assertions.assertNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullDevelopersTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        item.setDeveloperSet(new HashSet<>());
        Assertions.assertNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullPublishersTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        item.setPublisherSet(new HashSet<>());
        Assertions.assertNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addNullGenresTest() {
        when(repositoryMock.existsById(1)).thenReturn(false);

        item.setGenreSet(new HashSet<>());
        Assertions.assertNull(service.save(item), MESSAGE_ERROR);
    }

    @Test
    void addExistingSameRegionsTest() {
        when(repositoryMock.findByTitle(item.getTitle())).thenReturn(Optional.ofNullable(entity));
        when(regionRepositoryMock.save(any(RegionEntity.class))).thenReturn(new RegionEntity(ID));

        when(repositoryMock.save(any(GameEntity.class))).thenReturn(new GameEntity());
        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }


    @Test
    void addExistingDiffRegionsTest() {
        when(repositoryMock.findByTitle(item.getTitle())).thenReturn(Optional.ofNullable(entity));

        item.setRegionSet(new HashSet<>(List.of(new Region("reg1"))));
        when(repositoryMock.save(any(GameEntity.class))).thenReturn(new GameEntity());
        when(regionRepositoryMock.save(any(RegionEntity.class))).thenReturn(new RegionEntity());
        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }

    @Test
    void addExistingAndDiffRegionsTest() {
        when(repositoryMock.findByTitle(item.getTitle())).thenReturn(Optional.ofNullable(entity));


        when(regionRepositoryMock.findByName("reg1")).thenReturn(Optional.empty());
        when(regionRepositoryMock.findByName("reg2")).thenReturn(Optional.empty());
        when(repositoryMock.save(any(GameEntity.class))).thenReturn(new GameEntity());

        AtomicInteger callCount = new AtomicInteger(0);

        when(regionRepositoryMock.save(any(RegionEntity.class)))
                .thenAnswer(invocation -> {
                    RegionEntity input = invocation.getArgument(0);
                    int count = callCount.incrementAndGet();

                    if (count == 1) {
                        return new RegionEntity(1, input.getName());
                    } else if (count == 2) {
                        return new RegionEntity(2, input.getName());
                    } else {
                        throw new IllegalStateException("save() called more than twice in test");
                    }
                });


        Region region1 = new Region(NAME);
        Region region2 = new Region("reg2");
        region1.setId(ID);
        region2.setId(2);
        item.setRegionSet(new HashSet<>(List.of(region1, region2)));

        Assertions.assertNotNull(service.save(item), MESSAGE_ERROR);
    }

    @Test
    void addNullTest() {
        Assertions.assertNull(service.save(null), MESSAGE_ERROR);
    }

    @Test
    void addExistingExceptionTest() throws Exception {
        when(repositoryMock.findByTitle(item.getTitle())).thenReturn(Optional.ofNullable(entity));
        when(repositoryMock.save(any(GameEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(item), MESSAGE_ERROR);
    }

    @Test
    void addExceptionTest() throws Exception {
        when(regionRepositoryMock.save(any(RegionEntity.class))).thenReturn(new RegionEntity());
        when(platformRepositoryMock.save(any(PlatformEntity.class))).thenReturn(new PlatformEntity());
        when(developerRepositoryMock.save(any(DeveloperEntity.class))).thenReturn(new DeveloperEntity());
        when(publisherRepositoryMock.save(any(PublisherEntity.class))).thenReturn(new PublisherEntity());
        when(genreRepositoryMock.save(any(GenreEntity.class))).thenReturn(new GenreEntity());

        when(repositoryMock.save(any(GameEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(item), MESSAGE_ERROR);
    }
    @Test
    void addWithExistingTest() throws Exception {
        when(regionRepositoryMock.findByName(anyString())).thenReturn(Optional.of(new RegionEntity()));
        when(platformRepositoryMock.findByName(anyString())).thenReturn(Optional.of(new PlatformEntity()));
        when(developerRepositoryMock.findByName(anyString())).thenReturn(Optional.of(new DeveloperEntity()));
        when(publisherRepositoryMock.findByName(anyString())).thenReturn(Optional.of(new PublisherEntity()));
        when(genreRepositoryMock.findByName(anyString())).thenReturn(Optional.of(new GenreEntity()));

        when(repositoryMock.save(any(GameEntity.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> service.save(item), MESSAGE_ERROR);
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
    void updateForceExceptionTest() {
        Game item = new Game();
        item.setId(1);
        item.setTitle(NAME);

        GameEntity dbItemMock = mock(GameEntity.class);
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setTitle(NAME);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.update(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
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
    void deleteForceExceptionTest() {
        doThrow(new RuntimeException("Simulated delete error"))
                .when(repositoryMock).deleteGameDeveloperRelation(ID);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.delete(ID);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
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

    @Test
    void updatePictureNullTest() throws Exception {
        Assertions.assertNull(service.updateCoverArt(null), MESSAGE_ERROR);
    }

    @Test
    void updatePictureForceExceptionTest() {
        Game item = new Game();
        item.setId(1);
        item.setCoverArt(NAME);

        GameEntity dbItemMock = mock(GameEntity.class);
        when(repositoryMock.findById(item.getId())).thenReturn(Optional.of(dbItemMock));

        doThrow(new RuntimeException("Simulated exception")).when(dbItemMock).setCoverArt(NAME);

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            service.updateCoverArt(item);
        });

        Assertions.assertTrue(thrown.getMessage().contains("Invalid data"), MESSAGE_ERROR);
    }
}

