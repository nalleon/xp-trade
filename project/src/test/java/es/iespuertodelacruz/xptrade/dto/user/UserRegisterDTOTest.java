package es.iespuertodelacruz.xptrade.dto.user;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRegisterDTOTest extends TestUtilities {

    UserRegisterDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new UserRegisterDTO(NAME, EMAIL, PASSWORD);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, item.name(), MESSAGE_ERROR);
        Assertions.assertEquals(EMAIL, item.email(), MESSAGE_ERROR);
        Assertions.assertEquals(PASSWORD, item.password(), MESSAGE_ERROR);
    }
}
