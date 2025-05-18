package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.GameCollection;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface IGameCollectionRepository {
    GameCollection save(GameCollection gameCollection);
    List<GameCollection> findAll();
    GameCollection findById(Integer id);

    default Collection checkIfExists(User user, Game game) {
        return null;
    }

    default List<Collection> findAllLatest() {
        return null;
    }

    boolean delete(Integer id);
    GameCollection update(GameCollection gameCollection);
}
