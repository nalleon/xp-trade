package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreInputDTOTest extends TestUtilities {
    GenreInputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new GenreInputDTO(NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, item.name(), MESSAGE_ERROR);
    }
}
