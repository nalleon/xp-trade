package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface IPostService {
    Post add(Game game, User user, String content, String picture);
    Post findById(Integer id);
    Post findLatest(User user);
    List<Post> findByUser (User user);
    List<Post> findByGame (Game game);
    List<Post> findAll();
    List<Post> findAllLatest();

    boolean delete(Integer id);
    Post update(int id, Game game, User user, String content, String picture);
}
