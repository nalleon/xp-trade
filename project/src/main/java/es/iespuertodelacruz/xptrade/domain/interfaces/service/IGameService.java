package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.model.entities.*;

import java.util.List;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IGameService {
    Game add(String title, String coverArt, Set<Developer> developerSet, Set<Genre> genreSet,
             Set<Platform> platformSet, Set<Publisher> publisherSet,
             Set<Region> regionSet);

    Game findById(Integer id);
    Game findByTitle(String title);
    List<Game> findAll();
    List<Game> findAllByPlatform(Platform platform);
    List<Game> findAllByDeveloper(Developer developer);
    List<Game> findAllByPublisher(Publisher publisher);
    List<Game> findAllByGenre(Genre genre);
    List<Game> findAllByRegion(Region region);

    boolean delete(Integer id);
    Game update(int id, String title, String coverArt, Set<Developer> developerSet, Set<Genre> genreSet,
                Set<Platform> platformSet, Set<Publisher> publisherSet,
                Set<Region> regionSet);

    Game updateCoverArt(int id, String coverArt);
}
