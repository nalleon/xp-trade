package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameEntityTest extends TestUtilities {

    GameEntity item;
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
        item = new GameEntity(TITLE, COVER_ART, SLUG, developerSet, genreSet, platformSet, publisherSet, regionSet);
        item.setId(ID);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(TITLE, item.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(COVER_ART, item.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(developerSet, item.getDeveloperEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(genreSet, item.getGenreEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(platformSet, item.getPlatformEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherSet, item.getPublisherEntitySet(), MESSAGE_ERROR);
        Assertions.assertEquals(regionSet, item.getRegionEntitySet(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        item = new GameEntity(TITLE, COVER_ART, SLUG, new HashSet<>(developerSet),
                genreSet, platformSet, publisherSet, regionSet);
        item.setId(ID);
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(TITLE), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(COVER_ART), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(SLUG), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(genreSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(platformSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(regionSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(developerSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(publisherSet.toString()), MESSAGE_ERROR);

    }

    @Test
    public void equalsTest(){
        GameEntity equals = new GameEntity(TITLE, COVER_ART);
        equals.setId(ID);
        GameEntity differentId = new GameEntity(2);
        String str = "str";
        GameEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
