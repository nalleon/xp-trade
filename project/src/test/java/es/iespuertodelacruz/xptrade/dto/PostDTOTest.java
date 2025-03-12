package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PostDTOTest extends TestUtilities {
    PostDTO item;
    GameDTO game;
    UserDTO user;
    GenreDTO genre;
    RegionDTO region;
    PublisherDTO publisher;
    DeveloperDTO developer;
    PlatformDTO platform;
    Set<DeveloperDTO> developerSet;
    Set<GenreDTO> genreSet;
    Set<PlatformDTO> platformSet;
    Set<PublisherDTO> publisherSet;
    Set<RegionDTO> regionSet;

    @BeforeEach
    public void beforeEach() {
        genre = new GenreDTO(ID, NAME);
        region = new RegionDTO(ID, NAME);
        publisher = new PublisherDTO(ID, NAME);
        developer = new DeveloperDTO(ID, NAME);
        platform = new PlatformDTO(ID, NAME);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));

        game = new GameDTO(ID, TITLE, COVER_ART, developerSet, genreSet, platformSet, publisherSet, regionSet);
        user =new UserDTO(ID, USERNAME, EMAIL, PASSWORD, new RoleDTO(ID, ROLE_NAME), VERIFIED, VERIFICATION_TOKEN, CREATION_DATE, PROFILE_PICTURE);
        item = new PostDTO(ID, game, user, CONTENT, PICTURE, CREATION_DATE);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(game, item.game(), MESSAGE_ERROR);
        Assertions.assertEquals(user, item.user(), MESSAGE_ERROR);
        Assertions.assertEquals(CONTENT, item.content(), MESSAGE_ERROR);
        Assertions.assertEquals(PICTURE, item.picture(), MESSAGE_ERROR);
        Assertions.assertEquals(CREATION_DATE, item.creationDate(), MESSAGE_ERROR);
    }
}
