package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.*;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameInputDTOTest extends TestUtilities {
    GameInputDTO item;

    GenreInputDTO genre;
    RegionInputDTO region;
    PublisherInputDTO publisher;
    DeveloperInputDTO developer;
    PlatformInputDTO platform;
    Set<DeveloperInputDTO> developerSet;
    Set<GenreInputDTO> genreSet;
    Set<PlatformInputDTO> platformSet;
    Set<PublisherInputDTO> publisherSet;
    Set<RegionInputDTO> regionSet;

    @BeforeEach
    public void beforeEach() {
        genre = new GenreInputDTO(NAME);
        region = new RegionInputDTO(NAME);
        publisher = new PublisherInputDTO(NAME);
        developer = new DeveloperInputDTO(NAME);
        platform = new PlatformInputDTO(NAME);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        regionSet = new HashSet<>(Collections.singletonList(region));

        item = new GameInputDTO(ID, TITLE, COVER_ART, developerSet, genreSet, platformSet, publisherSet, regionSet);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(TITLE, item.title(), MESSAGE_ERROR);
        Assertions.assertEquals(COVER_ART, item.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(developerSet, item.developerDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(genreSet, item.genreInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(platformSet, item.platformDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherSet, item.publisherDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(regionSet, item.regionDTOSet(), MESSAGE_ERROR);
    }

}
