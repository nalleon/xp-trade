package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegionEntityTest extends TestUtilities {
    RegionEntity item;

    @BeforeEach
    public void beforeEach(){
        item = new RegionEntity();
        item = new RegionEntity(NAME);

        item.setId(ID);
        item.setName(NAME);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, item.getName(), MESSAGE_ERROR);
    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(NAME), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        RegionEntity equals = new RegionEntity(ID);
        RegionEntity differentId = new RegionEntity(2);
        String str = "str";
        RegionEntity nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
