package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PostTest extends MapperHelper {
    Post item;


    @Test
    public void getSetTest(){
        item = new Post(gameDomain, userDomain, CONTENT, PICTURE);
        item.setId(ID);
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(gameDomain, item.getGame(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain, item.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(CONTENT, item.getContent(), MESSAGE_ERROR);
        Assertions.assertEquals(PICTURE, item.getPicture(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        item = new Post(gameDomain, userDomain, CONTENT, PICTURE);
        item.setId(ID);
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(gameDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(userDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(CONTENT), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(PICTURE), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        item = new Post(ID);
        Post equals = new Post(ID);
        Post differentId = new Post(2);
        String str = "str";
        Post nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
