package es.iespuertodelacruz.xptrade.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestUtilities {
    public static final String MESSAGE_ERROR = "Expected result not found";

    /**
     * Constants
     */

    public static final  String defaultDate = "2025-03-12";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static final String ROLE_NAME = "ROLE_TEST";
    public static final int ID = 1;
    public static final String USERNAME = "usernameTest";
    public static final String EMAIL = "test@email.com";
    public static final String PASSWORD = "passwordTest";
    public static final Date CREATION_DATE;

    static {
        try {
            CREATION_DATE = sdf.parse(defaultDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String VERIFICATION_TOKEN = UUID.randomUUID().toString();
    public static final String PROFILE_PICTURE = "pfpTest.png";
    public static final int VERIFIED = 0;
    public static final String NAME = "genericNameTest";
    public static final String TITLE = "titleTest";
    public static final String SLUG = "slugTest";
    public static final String COVER_ART = "coverArtTest";
    public static final String CONTENT = "contentTest";
    public static final String PICTURE = "pictureTest.png";


}
