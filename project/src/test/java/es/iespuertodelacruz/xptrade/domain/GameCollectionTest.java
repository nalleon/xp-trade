package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameCollectionTest extends MapperHelper {


    GameCollection item;
    
    @Test
    public void getSetTest(){
        item = new GameCollection();
        item.setId(ID);
        item.setGame(gameDomain);
        item.setCollection(collectionDomain);
        item.setPlatform(platformDomain);
        item.setRegion(regionDomain);

        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain, item.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionDomain, item.getCollection(), MESSAGE_ERROR);
        Assertions.assertEquals(platformDomain, item.getPlatform(), MESSAGE_ERROR);
        Assertions.assertEquals(regionDomain, item.getRegion(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        item = new GameCollection();
        item.setId(ID);
        item.setGame(gameDomain);
        item.setCollection(collectionDomain);
        item.setPlatform(platformDomain);
        item.setRegion(regionDomain);

        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(gameDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(collectionDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(platformDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(regionDomain.toString()), MESSAGE_ERROR);

    }

    @Test
    public void equalsTest(){
        item = new GameCollection();
        item.setId(ID);
        GameCollection equals = new GameCollection(ID);
        GameCollection differentId = new GameCollection(2);
        String str = "str";
        GameCollection nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
