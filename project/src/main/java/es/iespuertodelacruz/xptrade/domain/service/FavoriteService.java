package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericSocialRepository;

import java.util.List;

public class FavoriteService implements IGenericSocialRepository<Favorite, Integer, User, Game> {
    @Override
    public Favorite save(Favorite favorite) {
        return null;
    }

    @Override
    public List<Favorite> findAll() {
        return List.of();
    }

    @Override
    public Favorite findById(Integer id) {
        return null;
    }

    @Override
    public List<Favorite> findByUser(User user) {
        return List.of();
    }

    @Override
    public List<Favorite> findByGame(Game game) {
        return List.of();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Favorite update(Favorite favorite) {
        return null;
    }
}
