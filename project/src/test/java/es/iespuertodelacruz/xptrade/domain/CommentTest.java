package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentTest extends MapperHelper {
    Comment item;


    @Test
    public void getSetTest(){
        item = new Comment(postDomain, userDomain, CONTENT);
        item.setId(ID);
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(postDomain, item.getPost(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain, item.getUser(), MESSAGE_ERROR);
        Assertions.assertEquals(CONTENT, item.getContent(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        item = new Comment(postDomain, userDomain, CONTENT);
        item.setId(ID);
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(postDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(userDomain.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(CONTENT), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        item = new Comment(ID);
        Comment equals = new Comment(ID);
        Comment differentId = new Comment(2);
        String str = "str";
        Comment nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
