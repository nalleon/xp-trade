package es.iespuertodelacruz.xptrade.dto.output;

import java.util.Set;

public record GameOutputDTO(int id, String title, String coverArt, String slug, int rating, String released,
                            Set<DeveloperOutputDTO> developerOutputDTOSet,
                            Set<GenreOutputDTO> genreOutputDTOSet, Set<PlatformOutputDTO> platformOutputDTOSet,
                            Set<PublisherOutputDTO> publisherOutputDTOSet) {
}
