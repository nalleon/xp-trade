package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface IFavoriteService {
    Favorite add(Game game, User user);
    Favorite findById(Integer id);
    Favorite checkIfExists(User user, Game game);
    List<Favorite> findByUser (User user);
    List<Favorite> findByGame (Game game);
    List<Favorite> findAll();
    boolean delete(Integer id);
    Favorite update(int id, Game game, User user);
}
