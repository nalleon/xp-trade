package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Favorite;
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
        serviceName = "FavoriteSoapService",
        endpointInterface = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSocialSoapService")
@Component
public class FavoriteSoapService implements IGenericSocialSoapService<Favorite, Integer, User, Game> {
    /**
     * Properties
     */
    private IGenericSocialRepository<Favorite, Integer, User, Game> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGenericSocialRepository<Favorite, Integer, User, Game> service) {
        this.service = service;
    }

    @Override
    public Favorite save(Favorite favorite) {
        return service.save(favorite);
    }

    @Override
    public List<Favorite> findAll() {
        return service.findAll();
    }

    @Override
    public Favorite findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<Favorite> findAllByUser(User user) {
        return service.findAllByUser(user);
    }

    @Override
    public List<Favorite> findAllBySubject(Game game) {
        return service.findAllBySubject(game);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Favorite update(Favorite favorite) {
        return service.update(favorite);
    }
}
