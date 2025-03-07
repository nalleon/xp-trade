package es.iespuertodelacruz.xptrade.utilities;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.*;
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
    public static final String NAME = "genericNameTest";


    /**
     * Properties
     */
    public RoleEntity roleEntity;
    public Role roleDomain;

    public UserEntity userEntity;
    public User userDomain;

    public GenreEntity genreEntity;
    public Genre genreDomain;

    public PublisherEntity publisherEntity;
    public Publisher publisherDomain;

    public DeveloperEntity developerEntity;
    public Developer developerDomain;

    public PlatformEntity platformEntity;
    public Platform platformDomain;

    public RegionEntity regionEntity;
    public Region regionDomain;


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

        genreEntity = new GenreEntity();
        genreEntity.setId(ID);
        genreEntity.setName(NAME);


        genreDomain = new Genre();
        genreDomain.setId(ID);
        genreDomain.setName(NAME);

        developerEntity = new DeveloperEntity();
        developerEntity.setId(ID);
        developerEntity.setName(NAME);


        developerDomain = new Developer();
        developerDomain.setId(ID);
        developerDomain.setName(NAME);

        regionEntity = new RegionEntity();
        regionEntity.setId(ID);
        regionEntity.setName(NAME);


        regionDomain = new Region();
        regionDomain.setId(ID);
        regionDomain.setName(NAME);

        publisherEntity = new PublisherEntity();
        publisherEntity.setId(ID);
        publisherEntity.setName(NAME);


        publisherDomain = new Publisher();
        publisherDomain.setId(ID);
        publisherDomain.setName(NAME);

        platformEntity = new PlatformEntity();
        platformEntity.setId(ID);
        platformEntity.setName(NAME);

        platformDomain = new Platform();
        platformDomain.setId(ID);
        platformDomain.setName(NAME);


    }
}
