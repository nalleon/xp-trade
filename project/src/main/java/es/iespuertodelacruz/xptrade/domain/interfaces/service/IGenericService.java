package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import java.util.List;

public interface IGenericService<T, E, U> {

    T add(U name);
    T findById(E id);
    T findByName (U name);
    List<T> findAll();
    boolean delete(E id);
    T update(E id, U name);
}
