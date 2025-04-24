package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IFavoriteService;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Service
public class FavoriteService implements IFavoriteService {
    /**
     * Properties
     */
    IGenericSocialRepository<Favorite, Integer, User, Game> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericSocialRepository<Favorite, Integer, User, Game> repository) {
        this.repository = repository;
    }

    @Override
    public Favorite add(Game game, User user) {
        Favorite aux = new Favorite(user, game);
        return repository.save(aux);
    }

    @Override
    public Favorite findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Favorite> findByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Favorite> findByGame(Game game) {
        return repository.findAllBySubject(game);
    }

    @Override
    public List<Favorite> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Favorite update(int id, Game game, User user) {
        Favorite aux = new Favorite(user, game);
        aux.setId(id);
        return repository.update(aux);
    }
}
