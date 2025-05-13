package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FavoriteEntityTest extends MapperHelper {
    FavoriteEntity item;

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
    Set<RegionEntity> regionSet;

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
        regionSet = new HashSet<>(Collections.singletonList(region));

        game = new GameEntity(TITLE, COVER_ART, SLUG, developerSet, genreSet, platformSet, publisherSet, regionSet);
        game.setId(ID);
        user = new UserEntity(ID);
        item = new FavoriteEntity();
        item.setId(ID);
        item.setGame(game);
        item.setUser(user);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(game, item.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(user, item.getUser(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(game.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(user.toString()), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        FavoriteEntity equals = new FavoriteEntity(user,game);
        equals.setId(ID);

        FavoriteEntity differentId = new FavoriteEntity(2);
        String str = "str";
        FavoriteEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
