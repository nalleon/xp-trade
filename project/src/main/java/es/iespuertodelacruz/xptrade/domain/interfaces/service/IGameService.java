package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.*;

import java.util.List;
import java.util.Set;

public interface IGameService {
    Game add(String title, String coverArt, Set<DeveloperEntity> developerEntitySet, Set<GenreEntity> genreEntitySet,
             Set<PlatformEntity> platformEntitySet, Set<PublisherEntity> publisherEntitySet,
             Set<RegionEntity> regionEntitySet);

    Game findById(Integer id);
    Game findByTitle(String title);
    List<Game> findAllByPlatform(Platform platform);
    List<Game> findAllByDeveloper(Developer developer);
    List<Game> findAllByPublisher(Publisher publisher);
    List<Game> findAllByGenre(Genre genre);
    List<Game> findAllByRegion(Region region);

    boolean delete(Integer id);
    Game update(int id, String title, String coverArt, Set<DeveloperEntity> developerEntitySet, Set<GenreEntity> genreEntitySet,
                Set<PlatformEntity> platformEntitySet, Set<PublisherEntity> publisherEntitySet,
                Set<RegionEntity> regionEntitySet);

    Game updateCoverArt(int id, String coverArt);
}
