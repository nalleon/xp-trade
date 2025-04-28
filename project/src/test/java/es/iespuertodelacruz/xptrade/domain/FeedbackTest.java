package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedbackTest extends TestUtilities {
    Feedback item;

    String content;

    @BeforeEach
    public void beforeEach() {
        item = new Feedback();
        item.setId(TITLE);
        item.setContent(CONTENT);
    }

    @Test
    public void getSetTest() {
        Assertions.assertEquals(TITLE, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(CONTENT, item.getContent(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest() {
        Assertions.assertTrue(item.toString().contains(TITLE), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(CONTENT), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        Feedback equals = new Feedback(item.getId(), TITLE);

        Feedback differentId = new Feedback("no");
        String str = "str";
        Feedback nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(nullObject, item,  MESSAGE_ERROR);
        Assertions.assertNotEquals(str, item, MESSAGE_ERROR);
    }
}
