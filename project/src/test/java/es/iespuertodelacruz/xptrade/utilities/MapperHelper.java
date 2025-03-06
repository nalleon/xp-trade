package es.iespuertodelacruz.xptrade.utilities;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.UUID;

public class MapperHelper extends TestUtilities {

    /**
     * Constants
     */
    public static final String ROLE_NAME = "ROLE_TEST";
    public static final int ID = 1;
    public static final String USERNAME = "usernameTest";
    public static final String EMAIL = "test@email.com";
    public static final String PASSWORD = "passwordTest";
    public static final Date CREATION_DATE = new Date();
    public static final String VERIFICATION_TOKEN = UUID.randomUUID().toString();
    public static final String PROFILE_PICTURE = "pfpTest.png";
    public static final int VERIFIED = 0;


    /**
     * Properties
     */
    public RoleEntity roleEntity;
    public Role roleDomain;

    public UserEntity userEntity;
    public User userDomain;

    @BeforeEach
    public void beforeEach() {
        roleEntity = new RoleEntity();
        roleEntity.setId(ID);
        roleEntity.setName(ROLE_NAME);

        roleDomain = new Role();
        roleDomain.setId(ID);
        roleDomain.setName(ROLE_NAME);

        userEntity = new UserEntity();
        userEntity.setId(ID);
        userEntity.setUsername(USERNAME);
        userEntity.setEmail(EMAIL);
        userEntity.setPassword(PASSWORD);
        userEntity.setCreationDate(CREATION_DATE);
        userEntity.setVerificationToken(VERIFICATION_TOKEN);
        userEntity.setProfilePicture(PROFILE_PICTURE);
        userEntity.setRole(roleEntity);
        userEntity.setVerified(VERIFIED);

        userDomain = new User();
        userDomain.setId(ID);
        userDomain.setUsername(USERNAME);
        userDomain.setEmail(EMAIL);
        userDomain.setPassword(PASSWORD);
        userDomain.setCreationDate(CREATION_DATE);
        userDomain.setVerificationToken(VERIFICATION_TOKEN);
        userDomain.setProfilePicture(PROFILE_PICTURE);
        userDomain.setRole(roleDomain);
        userDomain.setVerified(VERIFIED);


    }
}
