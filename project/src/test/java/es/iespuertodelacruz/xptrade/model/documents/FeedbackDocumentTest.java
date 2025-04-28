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
        item.setContent(CONTENT);
    }

    @Test
    public void getSetTest() {
        Assertions.assertEquals(CONTENT, item.getContent(), MESSAGE_ERROR);
    }
}
