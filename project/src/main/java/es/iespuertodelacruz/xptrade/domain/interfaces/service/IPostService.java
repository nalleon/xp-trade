package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IPostService {
    Post add(Game game, User user, String content, String picture);
    Post findById(Integer id);
    List<Post> findByUser (User user);
    List<Post> findByGame (Game game);
    List<Post> findAll();
    boolean delete(Integer id);
    Post update(int id, Game game, User user, String content, String picture);
}
