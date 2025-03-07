package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericSocialRepository;

import java.util.List;

public class CollectionService implements IGenericSocialRepository<Collection, Integer, User, Game> {
    @Override
    public Collection save(Collection collection) {
        return null;
    }

    @Override
    public List<Collection> findAll() {
        return List.of();
    }

    @Override
    public Collection findById(Integer id) {
        return null;
    }

    @Override
    public List<Collection> findByUser(User user) {
        return List.of();
    }

    @Override
    public List<Collection> findByGame(Game game) {
        return List.of();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Collection update(Collection collection) {
        return null;
    }
}
