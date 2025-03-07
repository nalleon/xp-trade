package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.ICollectionService;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class CollectionService implements ICollectionService {

    /**
     * Properties
     */
    IGenericSocialRepository<Collection, Integer, User, Game> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericSocialRepository<Collection, Integer, User, Game> repository) {
        this.repository = repository;
    }

    @Override
    public Collection add(Game game, User user) {
        Collection aux = new Collection(game, user);
        return repository.save(aux);
    }

    @Override
    public Collection findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Collection> findByUser(User user) {
        return repository.findAllByUser(user);
    }

    @Override
    public List<Collection> findByGame(Game game) {
        return repository.findAllBySubject(game);
    }

    @Override
    public List<Collection> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Collection update(int id, Game game, User user) {
        Collection aux = new Collection(game, user);
        aux.setId(id);
        return repository.update(aux);
    }
}
