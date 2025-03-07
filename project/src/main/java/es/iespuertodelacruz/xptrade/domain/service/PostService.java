package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericSocialRepository;

import java.util.List;

public class PostService implements IGenericSocialRepository<Post, Integer, User, Game> {
    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return List.of();
    }

    @Override
    public Post findById(Integer id) {
        return null;
    }

    @Override
    public List<Post> findByUser(User user) {
        return List.of();
    }

    @Override
    public List<Post> findByGame(Game game) {
        return List.of();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Post update(Post post) {
        return null;
    }
}
