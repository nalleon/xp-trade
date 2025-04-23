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

public class CommentInputDTOTest extends TestUtilities {
    CommentInputDTO item;
    PostOutputDTO post;
    GameOutputDTO game;
    UserDTO user;
    GenreOutputDTO genre;
    RegionOutputDTO region;
    PublisherOutputDTO publisher;
    DeveloperOutputDTO developer;
    PlatformOutputDTO platform;
    Set<DeveloperOutputDTO> developerSet;
    Set<GenreOutputDTO> genreSet;
    Set<PlatformOutputDTO> platformSet;
    Set<PublisherOutputDTO> publisherSet;
    Set<RegionOutputDTO> regionSet;

    @BeforeEach
    public void beforeEach() {
        genre = new GenreOutputDTO(ID, NAME);
        region = new RegionOutputDTO(ID, NAME);
        publisher = new PublisherOutputDTO(ID, NAME);
        developer = new DeveloperOutputDTO(ID, NAME);
        platform = new PlatformOutputDTO(ID, NAME);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));

        game = new GameOutputDTO(ID, TITLE, COVER_ART, developerSet, genreSet, platformSet, publisherSet, regionSet);
        user =new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleOutputDTO(ID, ROLE_NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE);
        post = new PostOutputDTO(ID, game, user, CONTENT, PICTURE, CREATION_DATE);
        item = new CommentInputDTO(post, user, CONTENT);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(post, item.post(), MESSAGE_ERROR);
        Assertions.assertEquals(user, item.user(), MESSAGE_ERROR);
        Assertions.assertEquals(CONTENT, item.content(), MESSAGE_ERROR);
    }
}
