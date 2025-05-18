package es.iespuertodelacruz.xptrade.dto.output;

import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GameOutputDTOTest extends TestUtilities {
    GameOutputDTO item;

    GenreOutputDTO genre;
    TagOutputDTO tag;
    PublisherOutputDTO publisher;
    DeveloperOutputDTO developer;
    PlatformOutputDTO platform;
    Set<DeveloperOutputDTO> developerSet;
    Set<GenreOutputDTO> genreSet;
    Set<PlatformOutputDTO> platformSet;
    Set<PublisherOutputDTO> publisherSet;
    Set<TagOutputDTO> tagSet;

    @BeforeEach
    public void beforeEach() {
        genre = new GenreOutputDTO(ID, NAME);
        tag = new TagOutputDTO(ID, NAME);
        publisher = new PublisherOutputDTO(ID, NAME);
        developer = new DeveloperOutputDTO(ID, NAME);
        platform = new PlatformOutputDTO(ID, NAME);

        developerSet = new HashSet<>(Collections.singletonList(developer));
        genreSet = new HashSet<>(Collections.singletonList(genre));
        platformSet = new HashSet<>(Collections.singletonList(platform));
        publisherSet = new HashSet<>(Collections.singletonList(publisher));
        tagSet = new HashSet<>(Collections.singletonList(tag));

        item = new GameOutputDTO(ID,TITLE, COVER_ART, SLUG, RATING, RELEASED, tagSet, developerSet, genreSet, platformSet, publisherSet);
    }


    @Test
    public void getSetTest(){
        Assertions.assertEquals(ID, item.id(), MESSAGE_ERROR);
        Assertions.assertEquals(TITLE, item.title(), MESSAGE_ERROR);
        Assertions.assertEquals(COVER_ART, item.coverArt(), MESSAGE_ERROR);
        Assertions.assertEquals(RATING, item.rating(), MESSAGE_ERROR);
        Assertions.assertEquals(RELEASED, item.released(), MESSAGE_ERROR);
        Assertions.assertEquals(tagSet, item.tagOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(developerSet, item.developerOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(genreSet, item.genreOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(platformSet, item.platformOutputDTOSet(), MESSAGE_ERROR);
        Assertions.assertEquals(publisherSet, item.publisherOutputDTOSet(), MESSAGE_ERROR);
    }

}
