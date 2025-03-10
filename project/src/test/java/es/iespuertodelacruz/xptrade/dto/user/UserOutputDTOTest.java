package es.iespuertodelacruz.xptrade.dto.user;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserOutputDTOTest extends TestUtilities {
    UserOutputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new UserOutputDTO(NAME, EMAIL);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, item.username(), MESSAGE_ERROR);
        Assertions.assertEquals(EMAIL, item.email(), MESSAGE_ERROR);
    }
}
