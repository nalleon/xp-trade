package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ContentUpdateDTOTest extends TestUtilities {
    ContentUpdateDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new ContentUpdateDTO(CONTENT);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(CONTENT, item.content(), MESSAGE_ERROR);
    }
}
