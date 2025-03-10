package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserRegisterDTO;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleDTOTest extends TestUtilities {

    RoleDTO item;

    @BeforeEach
    public void beforeEach() {
        item = new RoleDTO(ROLE_NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ROLE_NAME, item.name(), MESSAGE_ERROR);
    }
}
