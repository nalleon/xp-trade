package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface ICollectionRepository {
    Collection save(Collection t);
    List<Collection> findAll();
    Collection findById(Integer id);

    default Collection checkIfExists(User user, Game game) {
        return null;
    }

    default List<Collection> findAllLatest() {
        return null;
    }

    List<Collection> findAllByUser(User u);
    boolean delete(Integer id);
    Collection update(Collection collection);
}
