package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSocialSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@WebService(endpointInterface = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSocialSoapService")
@Component
public class CommentSoapService implements IGenericSocialSoapService<Comment, Integer, User, Post> {
    /**
     * Properties
     */
    private IGenericSocialRepository<Comment, Integer, User, Post> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGenericSocialRepository<Comment, Integer, User, Post> service) {
        this.service = service;
    }

    @Override
    public Comment save(Comment comment) {
        return service.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return service.findAll();
    }

    @Override
    public Comment findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<Comment> findAllByUser(User user) {
        return service.findAllByUser(user);
    }

    @Override
    public List<Comment> findAllBySubject(Post post) {
        return service.findAllBySubject(post);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Comment update(Comment comment) {
        return service.update(comment);
    }
}
