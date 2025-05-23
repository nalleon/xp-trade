package es.iespuertodelacruz.xptrade.dto.user;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserSearchDTOTest extends TestUtilities {

    UserSearchDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new UserSearchDTO(NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(NAME, item.name(), MESSAGE_ERROR);
    }
}
