package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleOutputDTOTest extends TestUtilities {

    RoleOutputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new RoleOutputDTO(ID, ROLE_NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(ROLE_NAME, item.name(), MESSAGE_ERROR);
    }
}
