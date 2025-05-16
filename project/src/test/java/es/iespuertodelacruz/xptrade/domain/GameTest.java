package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameTest extends MapperHelper {
    Game item;

    Set<Developer> developerSet= new HashSet<>(Collections.singletonList(developerDomain));

    Set<Genre> genreSet = new HashSet<>(Collections.singletonList(genreDomain));

    Set<Platform> platformSet= new HashSet<>(Collections.singletonList(platformDomain));

    Set<Publisher> publisherSet= new HashSet<>(Collections.singletonList(publisherDomain));


    @Test
    public void getSetTest(){
        item = new Game(TITLE, COVER_ART, SLUG, new HashSet<>(developerSet),
                genreSet, platformSet, publisherSet);

        item.setId(ID);

        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(TITLE, item.getTitle(), MESSAGE_ERROR);
        Assertions.assertEquals(COVER_ART, item.getCoverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(developerSet, item.getDeveloperSet(), MESSAGE_ERROR);
        Assertions.assertEquals(genreSet, item.getGenreSet(), MESSAGE_ERROR);
        Assertions.assertEquals(platformSet, item.getPlatformSet(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherSet, item.getPublisherSet(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        item = new Game(TITLE, COVER_ART,SLUG, new HashSet<>(developerSet),
                genreSet, platformSet, publisherSet);
        item.setId(ID);
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(TITLE), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(COVER_ART), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(genreSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(platformSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(developerSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(publisherSet.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(SLUG), MESSAGE_ERROR);

    }

    @Test
    public void equalsTest(){
        item = new Game(COVER_ART);
        item.setId(ID);
        Game equals = new Game(ID);
        Game differentId = new Game(2);
        String str = "str";
        Game nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
