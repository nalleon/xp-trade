package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest extends TestUtilities {

    public static final Role ROLE = new Role(ID, ROLE_NAME);
    User item;

    @BeforeEach
    public void beforeEach(){
        item = new User();
        item = new User(NAME, PASSWORD, EMAIL);
        item = new User(NAME, PASSWORD, EMAIL, VERIFIED);
        item = new User(NAME, PASSWORD, EMAIL, PROFILE_PICTURE);

        item.setId(ID);
        item.setUsername(NAME);
        item.setEmail(EMAIL);
        item.setPassword(PASSWORD);
        item.setRole(ROLE);
        item.setCreationDate(CREATION_DATE);
        item.setProfilePicture(PROFILE_PICTURE);
        item.setVerificationToken(VERIFICATION_TOKEN);
        item.setVerified(VERIFIED);
    }

    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(NAME, item.getUsername(), MESSAGE_ERROR);
        Assertions.assertEquals(EMAIL, item.getEmail(), MESSAGE_ERROR);
        Assertions.assertEquals(PASSWORD, item.getPassword(), MESSAGE_ERROR);
        Assertions.assertEquals(ROLE, item.getRole(), MESSAGE_ERROR);
        Assertions.assertEquals(CREATION_DATE, item.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(PROFILE_PICTURE, item.getProfilePicture(), MESSAGE_ERROR);
        Assertions.assertEquals(VERIFICATION_TOKEN, item.getVerificationToken(), MESSAGE_ERROR);
        Assertions.assertEquals(VERIFIED, item.getVerified(), MESSAGE_ERROR);

    }

    @Test
    public void toStringTest(){
        Assertions.assertTrue(item.toString().contains(String.valueOf(ID)), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(NAME), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(EMAIL), MESSAGE_ERROR);
        Assertions.assertFalse(item.toString().contains(PASSWORD), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(ROLE.getName()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(CREATION_DATE.toString()), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(PROFILE_PICTURE), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(VERIFICATION_TOKEN), MESSAGE_ERROR);
        Assertions.assertTrue(item.toString().contains(String.valueOf(VERIFIED)), MESSAGE_ERROR);
    }

    @Test
    public void equalsTest(){
        User equals = new User(ID);
        User differentId = new User(2);
        String str = "str";
        User nullObject = null;

        Assertions.assertEquals(item, equals, MESSAGE_ERROR);
        Assertions.assertEquals(item, item, MESSAGE_ERROR);
        Assertions.assertEquals(item.hashCode(), equals.hashCode(), MESSAGE_ERROR);
        Assertions.assertNotEquals(item, differentId, MESSAGE_ERROR);
        Assertions.assertNotEquals(item,nullObject,  MESSAGE_ERROR);
        Assertions.assertNotEquals(item, str, MESSAGE_ERROR);
    }
}
