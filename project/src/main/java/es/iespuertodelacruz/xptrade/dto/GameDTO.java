package es.iespuertodelacruz.xptrade.dto;

import java.util.Set;

public record GameDTO(int id, String title, String coverArt, Set<DeveloperDTO> developerDTOSet,
                      Set<GenreDTO> genreDTOSet, Set<PlatformDTO> platformDTOSet,
                      Set<PublisherDTO> publisherDTOSet,
                      Set<RegionDTO> regionDTOSet) {
}
