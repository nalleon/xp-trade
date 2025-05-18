package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.*;

import java.util.Set;

public record GameInputDTO(String title, String coverArt, String slug, int rating, String released,
                           Set<TagInputDTO> tagInputDTOSet, Set<DeveloperInputDTO> developerInputDTOSet,
                           Set<GenreInputDTO> genreInputDTOSet, Set<PlatformInputDTO> platformInputDTOSet,
                           Set<PublisherInputDTO> publisherInputDTOSet) {
}
