package es.iespuertodelacruz.xptrade.dto;

import java.util.Set;

public record GameDTO(int id, String name, String coverArt, Set<DeveloperDTO> developerSet,
                      Set<GenreDTO> genreSet, Set<PlatformDTO> platformSet, Set<PublisherDTO> publisherSet,
                      Set<RegionDTO> regionSet) {
}
