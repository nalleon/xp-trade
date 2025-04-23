package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.*;

import java.util.Set;

public record GameInputDTO(int id, String title, String coverArt, Set<DeveloperInputDTO> developerDTOSet,
                           Set<GenreInputDTO> genreInputDTOSet, Set<PlatformInputDTO> platformDTOSet,
                           Set<PublisherInputDTO> publisherDTOSet,
                           Set<RegionInputDTO> regionDTOSet) {
}
