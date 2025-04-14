package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlatformOutputDTOTest extends TestUtilities {
    PlatformOutputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new PlatformOutputDTO(ID, NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, item.name(), MESSAGE_ERROR);
    }
}
