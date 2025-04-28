package es.iespuertodelacruz.xptrade.model.documents;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedbackDocumentTest extends TestUtilities {
    FeedbackDocument item;

    String content;

    @BeforeEach
    public void beforeEach() {
        item = new FeedbackDocument();
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
        FeedbackDocument equals = new FeedbackDocument(item.getId(), TITLE);

        FeedbackDocument differentId = new FeedbackDocument();
        equals.setId("no");
        String str = "str";
        FeedbackDocument nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
