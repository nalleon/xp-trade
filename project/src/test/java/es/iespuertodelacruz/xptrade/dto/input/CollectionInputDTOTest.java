package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.*;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionInputDTOTest extends TestUtilities {
    CollectionInputDTO item;
    GameInputDTO game;
    UserDTO user;

    @BeforeEach
    public void beforeEach() {
        game = new GameInputDTO(TITLE, COVER_ART, SLUG, RATING, RELEASED, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        user =new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, ROLE_NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE);
        item = new CollectionInputDTO(user);

    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(user, item.user(), MESSAGE_ERROR);
    }
}
