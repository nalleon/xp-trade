package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.input.RoleInputDTO;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleInputDTOTest extends TestUtilities {

    RoleInputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new RoleInputDTO(ROLE_NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ROLE_NAME, item.name(), MESSAGE_ERROR);
    }
}
