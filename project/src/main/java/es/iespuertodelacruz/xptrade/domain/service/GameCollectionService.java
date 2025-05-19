package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameCollectionRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGameCollectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GameCollectionService implements IGameCollectionService {

    /**
     * Properties
     */
    IGameCollectionRepository repository;

    @Autowired
    public void setRepository(IGameCollectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public GameCollection add(Game game, Collection collection, Region region, Platform platform) {
        GameCollection gameCollection = new GameCollection();
        gameCollection.setGame(game);
        gameCollection.setCollection(collection);
        gameCollection.setRegion(region);
        gameCollection.setPlatform(platform);
        return repository.save(gameCollection);
    }

    @Override
    public GameCollection findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<GameCollection> findByCollection(Collection collection) {
        return repository.findAllByCollection(collection);
    }

    @Override
    public List<GameCollection> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public GameCollection update(int id, Game game, Collection collection, Region region, Platform platform) {
        GameCollection gameCollection = new GameCollection(id, game, collection, region, platform);
        return repository.update(gameCollection);
    }
}
