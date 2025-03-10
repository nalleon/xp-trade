package es.iespuertodelacruz.xptrade.dto.user;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserUpdateInputDTOTest extends TestUtilities {

    UserUpdateInputDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new UserUpdateInputDTO(EMAIL, PASSWORD);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(EMAIL, item.email(), MESSAGE_ERROR);
        Assertions.assertEquals(PASSWORD, item.password(), MESSAGE_ERROR);
    }


}
