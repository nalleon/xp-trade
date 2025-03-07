package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.ICommentService;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class CommentService  implements ICommentService {
    /**
     * Properties
     */
    IGenericSocialRepository<Comment, Integer, User, Post> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericSocialRepository<Comment, Integer, User, Post> repository) {
        this.repository = repository;
    }

    @Override
    public Comment add(Post post, User user, String content) {
        Comment aux = new Comment(post, user, content);
        return repository.save(aux);
    }

    @Override
    public Comment findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Comment> findByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return repository.findAllBySubject(post);
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Comment update(int id, Post post, User user, String content) {
        Comment aux = new Comment(post, user, content);
        aux.setId(id);
        return repository.update(aux);
    }
}
