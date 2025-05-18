package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.domain.*;
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
    TagInputDTO tag;
    Set<TagInputDTO> tagSet;


    @BeforeEach
    public void beforeEach() {
        genre = new GenreInputDTO(NAME);
        region = new RegionInputDTO(NAME);
        publisher = new PublisherInputDTO(NAME);
        developer = new DeveloperInputDTO(NAME);
        platform = new PlatformInputDTO(NAME);
        tag = new TagInputDTO(NAME);

        tagSet = new HashSet<>(Collections.singletonList(tag));
        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));

        item = new GameInputDTO(TITLE, COVER_ART, SLUG, RATING, RELEASED, tagSet,
                developerSet, genreSet, platformSet, publisherSet);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(TITLE, item.title(), MESSAGE_ERROR);
        Assertions.assertEquals(COVER_ART, item.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(SLUG, item.slug(), MESSAGE_ERROR);
        Assertions.assertEquals(RATING, item.rating(), MESSAGE_ERROR);
        Assertions.assertEquals(RELEASED, item.released(), MESSAGE_ERROR);
        Assertions.assertEquals(developerSet, item.developerInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(genreSet, item.genreInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(platformSet, item.platformInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherSet, item.publisherInputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(tagSet, item.tagInputDTOSet(), MESSAGE_ERROR);

    }

}
