package es.iespuertodelacruz.xptrade.dto.output;

import java.util.Set;

public record GameOutputDTO(int id, String title, String coverArt, Set<DeveloperOutputDTO> developerOutputDTOSet,
                            Set<GenreOutputDTO> genreOutputDTOSet, Set<PlatformOutputDTO> platformOutputDTOSet,
                            Set<PublisherOutputDTO> publisherOutputDTOSet,
                            Set<RegionOutputDTO> regionOutputDTOSet) {
}
