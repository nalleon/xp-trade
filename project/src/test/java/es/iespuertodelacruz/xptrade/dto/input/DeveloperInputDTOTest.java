package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeveloperInputDTOTest extends TestUtilities {
    DeveloperInputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new DeveloperInputDTO(NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, item.name(), MESSAGE_ERROR);
    }
}
