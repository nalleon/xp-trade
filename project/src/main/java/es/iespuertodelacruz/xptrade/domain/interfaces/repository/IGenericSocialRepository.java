package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface IGenericSocialRepository <T, E, U, G> {
    T save(T t);
    List<T> findAll();
    T findById(E id);

    default T checkIfExists(User user, Game game) {
        return null;
    }

    List<T> findAllByUser(U u);
    List<T> findAllBySubject(G g);
    boolean delete(E id);
    T update(T t);
}
