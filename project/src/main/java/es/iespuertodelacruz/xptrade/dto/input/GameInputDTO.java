package es.iespuertodelacruz.xptrade.dto.input;

import es.iespuertodelacruz.xptrade.dto.output.*;

import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public record GameInputDTO(int id, String title, String coverArt, Set<DeveloperOutputDTO> developerOutputDTOSet,
                           Set<GenreOutputDTO> genreOutputDTOSet, Set<PlatformOutputDTO> platformOutputDTOSet,
                           Set<PublisherOutputDTO> publisherOutputDTOSet,
                           Set<RegionOutputDTO> regionOutputDTOSet) {
}
