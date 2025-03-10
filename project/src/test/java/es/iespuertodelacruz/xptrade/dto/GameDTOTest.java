package es.iespuertodelacruz.xptrade.dto;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameDTOTest extends TestUtilities {
    GameDTO item;

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

        item = new GameDTO(ID, TITLE, COVER_ART, developerSet, genreSet, platformSet, publisherSet, regionSet);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(TITLE, item.title(), MESSAGE_ERROR);
        Assertions.assertEquals(COVER_ART, item.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(developerSet, item.developerSet(), MESSAGE_ERROR);
        Assertions.assertEquals(genreSet, item.genreSet(), MESSAGE_ERROR);
        Assertions.assertEquals(platformSet, item.platformSet(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherSet, item.publisherSet(), MESSAGE_ERROR);
        Assertions.assertEquals(regionSet, item.regionSet(), MESSAGE_ERROR);
    }

}
