package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface ICollectionService {
    Collection add(Game game, User user);
    Collection findById(Integer id);
    List<Collection> findByUser (User user);
    List<Collection> findByGame (Game game);
    List<Collection> findAll();
    boolean delete(Integer id);
    Collection update(int id, Game game, User user);
}
