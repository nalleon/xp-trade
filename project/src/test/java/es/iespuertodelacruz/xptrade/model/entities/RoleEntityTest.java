package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleEntityTest extends TestUtilities {
    RoleEntity item;

    @BeforeEach
    public void beforeEach(){
        item = new RoleEntity();
        item = new RoleEntity(NAME);

        item.setId(ID);
        item.setName(ROLE_NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(ROLE_NAME, item.getName(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(ROLE_NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        RoleEntity equals = new RoleEntity(ID);
        RoleEntity differentId = new RoleEntity(2);
        String str = "str";
        RoleEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
