package es.iespuertodelacruz.xptrade.shared.utils;

import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomApiResponseTest extends TestUtilities {
    public static final int STATUS = 1;
    public static final String MESSAGE = "nameTest";
    public static final RoleDTO DATA = new RoleDTO(ID, ROLE_NAME);
    CustomApiResponse<RoleDTO> response;

    @BeforeEach
    public void beforeEach(){
        response = new CustomApiResponse<RoleDTO>(2, "", new RoleDTO(2, ROLE_NAME));
    }

    @Test
    public void getSetTest(){
        response.setStatus(STATUS);
        response.setMessage(MESSAGE);
        response.setData(DATA);
        Assertions.assertEquals(STATUS, response.getStatus(), MESSAGE_ERROR);
        Assertions.assertEquals(MESSAGE, response.getMessage(), MESSAGE_ERROR);
        Assertions.assertEquals(DATA, response.getData(), MESSAGE_ERROR);
    }

}
