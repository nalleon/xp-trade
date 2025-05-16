package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionEntityTest extends TestUtilities {
    CollectionEntity item;

    GameEntity game;
    UserEntity user;
    GenreEntity genre;
    RegionEntity region;
    PublisherEntity publisher;
    DeveloperEntity developer;
    PlatformEntity platform;
    Set<DeveloperEntity> developerSet;
    Set<GenreEntity> genreSet;
    Set<PlatformEntity> platformSet;
    Set<PublisherEntity> publisherSet;

    @BeforeEach
    public void beforeEach() {
        genre = new GenreEntity(NAME);
        region = new RegionEntity(NAME);
        publisher = new PublisherEntity(NAME);
        developer = new DeveloperEntity(NAME);
        platform = new PlatformEntity(NAME);

        genre.setId(ID);
        region.setId(ID);
        publisher.setId(ID);
        developer.setId(ID);
        platform.setId(ID);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));

        game = new GameEntity(TITLE, COVER_ART, SLUG, developerSet, genreSet, platformSet, publisherSet);
        game.setId(ID);
        user = new UserEntity(ID);
        item = new CollectionEntity();
        item.setId(ID);
        item.setUser(user);
    }
    
    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(user, item.getUser(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(user.toString()), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        CollectionEntity equals = new CollectionEntity(user);
        equals.setId(ID);

        CollectionEntity differentId = new CollectionEntity(2);
        String str = "str";
        CollectionEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
