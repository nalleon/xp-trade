package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import java.util.List;

public interface IGenericRepository<T, E, U> {
    T save(T t);
    List<T> findAll();
    T findById(E id);
    T findByName(U name);
    boolean delete(E id);
    T update(T t);
}
