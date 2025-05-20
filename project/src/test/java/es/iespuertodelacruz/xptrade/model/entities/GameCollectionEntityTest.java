package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameCollectionEntityTest extends MapperHelper {


    GameCollectionEntity item;
    
    @Test
    public void getSetTest(){
        item = new GameCollectionEntity();
        item.setId(ID);
        item.setGame(gameEntity);
        item.setCollection(collectionEntity);
        item.setPlatform(platformEntity);
        item.setRegion(regionEntity);

        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameEntity, item.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(collectionEntity, item.getCollection(), MESSAGE_ERROR);
        Assertions.assertEquals(platformEntity, item.getPlatform(), MESSAGE_ERROR);
        Assertions.assertEquals(regionEntity, item.getRegion(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        item = new GameCollectionEntity();
        item.setId(ID);
        item.setGame(gameEntity);
        item.setCollection(collectionEntity);
        item.setPlatform(platformEntity);
        item.setRegion(regionEntity);

        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(gameEntity.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(collectionEntity.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(platformEntity.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(regionEntity.toString()), MESSAGE_ERROR);

    }

    @Test
    public void equalsTest(){
        item = new GameCollectionEntity();
        item.setId(ID);
        GameCollectionEntity equals = new GameCollectionEntity(ID);
        GameCollectionEntity differentId = new GameCollectionEntity(2);
        String str = "str";
        GameCollectionEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
