package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface ICommentService {
    Comment add(Post post, User user, String content);
    Comment findById(Integer id);
    List<Comment> findByUser (User user);
    List<Comment> findByPost (Post post);
    List<Comment> findAll();
    boolean delete(Integer id);
    Comment update(int id, Post post, User user, String content);
}
