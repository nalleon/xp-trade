package es.iespuertodelacruz.xptrade.domain.interfaces;

import java.util.List;

public interface IGenericSocialRepository <T, E, U, G> {
    T save(T t);
    List<T> findAll();
    T findById(E id);
    List<T> findByUser(U u);
    List<T> findByGame(G g);
    boolean delete(E id);
    T update(T t);
}
