package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IGameSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@WebService(
        serviceName = "GameSoapService",
        endpointInterface = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGameSoapService")
@Component
public class GameSoapService implements IGameSoapService {
    /**
     * Properties
     */
    private IGameRepository service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGameRepository service) {
        this.service = service;
    }

    @Override
    public Game save(Game game) {
        return service.save(game);
    }

    @Override
    public List<Game> findAll() {
        return service.findAll();
    }

    @Override
    public List<Game> findAllByPlatform(Platform platform) {
        return service.findAllByPlatform(platform);
    }

    @Override
    public List<Game> findAllByDeveloper(Developer developer) {
        return service.findAllByDeveloper(developer);
    }

    @Override
    public List<Game> findAllByPublisher(Publisher publisher) {
        return service.findAllByPublisher(publisher);
    }

    @Override
    public List<Game> findAllByGenre(Genre genre) {
        return service.findAllByGenre(genre);
    }

    @Override
    public List<Game> findAllByRegion(Region region) {
        return service.findAllByRegion(region);
    }

    @Override
    public Game findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Game findByTitle(String title) {
        return service.findByTitle(title);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Game update(Game game) {
        return service.update(game);
    }

    @Override
    public Game updateCoverArt(Game game) {
        return service.updateCoverArt(game);
    }
}
