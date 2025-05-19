package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface ICollectionService {
    Collection add(User user);
    Collection findById(Integer id);
    List<Collection> findByUser (User user);
    List<Collection> findAll();
    boolean delete(Integer id);
    Collection update(int id, User user);
}
