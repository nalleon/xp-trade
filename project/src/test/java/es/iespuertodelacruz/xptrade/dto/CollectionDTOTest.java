package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.dto.user.UserOutputDTO;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionDTOTest extends TestUtilities {
    CollectionDTO item;
    GameDTO game;
    UserOutputDTO user;
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
        user = new UserOutputDTO(NAME, EMAIL);
        item = new CollectionDTO(ID, game, user);

    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(game, item.game(), MESSAGE_ERROR);
        Assertions.assertEquals(user, item.user(), MESSAGE_ERROR);
    }
}
