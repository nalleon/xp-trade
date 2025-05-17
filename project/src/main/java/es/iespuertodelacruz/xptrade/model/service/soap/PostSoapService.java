package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
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
@WebService(
        serviceName = "PostSoapService",
        endpointInterface = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSocialSoapService")
@Component
public class PostSoapService implements IGenericSocialSoapService<Post, Integer, User, Game> {
    /**
     * Properties
     */
    private IGenericSocialRepository<Post, Integer, User, Game> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGenericSocialRepository<Post, Integer, User, Game> service) {
        this.service = service;
    }

    @Override
    public Post save(Post post) {
        return service.save(post);
    }

    @Override
    public List<Post> findAll() {
        return service.findAll();
    }

    @Override
    public Post findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<Post> findAllByUser(User user) {
        return service.findAllByUser(user);
    }

    @Override
    public List<Post> findAllBySubject(Game game) {
        return service.findAllBySubject(game);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Post update(Post post) {
        return service.update(post);
    }
}
