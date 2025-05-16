package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameService;
import es.iespuertodelacruz.xptrade.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class GameService implements IGameService {
    /**
     * Properties
     */
    IGameRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGameRepository repository) {
        this.repository = repository;
    }

    @Override
    public Game add(String title, String coverArt, String slug, Set<Developer> developerSet,
                    Set<Genre> genreSet, Set<Platform> platformSet,
                    Set<Publisher> publisherSet) {

        Game aux = new Game(title, coverArt, slug, developerSet, genreSet, platformSet,
                publisherSet);
        return repository.save(aux);
    }

    @Override
    public Game findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Game findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Game> findAllByPlatform(Platform platform) {
        return repository.findAllByPlatform(platform);
    }

    @Override
    public List<Game> findAllByDeveloper(Developer developer) {
        return repository.findAllByDeveloper(developer);
    }

    @Override
    public List<Game> findAllByPublisher(Publisher publisher) {
        return repository.findAllByPublisher(publisher);
    }

    @Override
    public List<Game> findAllByGenre(Genre genre) {
        return repository.findAllByGenre(genre);
    }

    @Override
    public List<Game> findAllByRegion(Region region) {
        return repository.findAllByRegion(region);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Game update(int id, String title, String slug, String coverArt, Set<Developer> developerSet, Set<Genre> genreSet, Set<Platform> platformSet, Set<Publisher> publisherSet) {
        Game aux = new Game(title, coverArt, slug, developerSet, genreSet, platformSet,
                publisherSet);
        aux.setId(id);
        return repository.update(aux);
    }

    @Override
    public Game updateCoverArt(int id, String coverArt) {
        Game aux = new Game(coverArt);
        aux.setId(id);
        return repository.updateCoverArt(aux);
    }
}
