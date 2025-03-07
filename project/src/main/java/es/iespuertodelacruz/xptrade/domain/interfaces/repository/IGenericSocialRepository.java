package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import java.util.List;

public interface IGenericSocialRepository <T, E, U, G> {
    T save(T t);
    List<T> findAll();
    T findById(E id);
    List<T> findAllByUser(U u);
    List<T> findAllBySubject(G g);
    boolean delete(E id);
    T update(T t);
}
