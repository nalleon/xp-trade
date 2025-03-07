package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.*;

import java.util.List;

public interface IGameRepository {
    Game save(Game game);
    List<Game> findAll();
    List<Game> findAllByPlatform(Platform platform);
    List<Game> findAllByDeveloper(Developer developer);
    List<Game> findAllByPublisher(Publisher publisher);
    List<Game> findAllByGenre(Genre genre);
    List<Game> findAllByRegion(Region region);
    Game findById(Integer id);
    Game findByTitle(String title);
    boolean delete(Integer id);
    Game update(Game game);
    Game updateCoverArt(Game game);
}
