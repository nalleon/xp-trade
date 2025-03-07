package es.iespuertodelacruz.xptrade.domain.interfaces;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Post;

import java.util.Date;
import java.util.List;

public interface IPostService {
    Post add(String gameTitle, String username, String content, String picture, Date creationDate);
    Post findById(Integer id);
    List<Post> findByUser (String username);
    List<Post> findByGame (String title);
    List<Post> findAll();
    boolean delete(Integer id);
    Post update(int id, String name);
}
