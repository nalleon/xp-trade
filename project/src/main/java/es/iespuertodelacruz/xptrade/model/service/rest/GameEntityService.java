package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.*;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameRepository;
import es.iespuertodelacruz.xptrade.mapper.IGameEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import es.iespuertodelacruz.xptrade.model.repository.IGameEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class GameEntityService implements IGameRepository {

    /**
     * Properties
     */
    IGameEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGameEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Game save(Game game) {
        if(game == null){
            return null;
        }

        try {
            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            GameEntity savedEntity = repository.save(entity);
            return IGameEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Game> findAll() {
        List<GameEntity> listEntities = repository.findAll();
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByPlatform(Platform platform) {
        List<GameEntity> listEntities = repository.findAllByPlatform(platform.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByDeveloper(Developer developer) {
        List<GameEntity> listEntities = repository.findAllByDeveloper(developer.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByPublisher(Publisher publisher) {
        List<GameEntity> listEntities = repository.findAllByPublisher(publisher.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByGenre(Genre genre) {
        List<GameEntity> listEntities = repository.findAllByGenre(genre.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Game> findAllByRegion(Region region) {
        List<GameEntity> listEntities = repository.findAllByRegion(region.getId());
        return IGameEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Game findById(Integer id) {
        GameEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null) {
            return IGameEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public Game findByTitle(String title) {
        GameEntity entityFound = repository.findByTitle(title).orElse(null);

        if (entityFound != null) {
            return IGameEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        try {
            repository.deleteGameDeveloperRelation(id);
            repository.deleteGamePlatformRelation(id);
            repository.deleteGamePublisherRelation(id);
            repository.deleteGameRegionRelation(id);
            repository.deleteGameGenreRelation(id);
            int quantity = repository.deleteEntityById(id);
            return quantity > 0;
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    @Transactional
    public Game update(Game game) {
        if(game == null){
            return null;
        }

        GameEntity dbItem = repository.findById(game.getId()).orElse(null);
        if(dbItem == null){
            return null;
        }

        try {
            GameEntity entity = IGameEntityMapper.INSTANCE.toEntity(game);
            dbItem.setTitle(entity.getTitle());
            dbItem.setCoverArt(entity.getCoverArt());
            dbItem.setDeveloperEntitySet(entity.getDeveloperEntitySet());
            dbItem.setGenreEntitySet(entity.getGenreEntitySet());
            dbItem.setPlatformEntitySet(entity.getPlatformEntitySet());
            dbItem.setRegionEntitySet(entity.getRegionEntitySet());
            dbItem.setPublisherEntitySet(entity.getPublisherEntitySet());

            return IGameEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }

    }

    @Override
    @Transactional
    public Game updateCoverArt(Game game) {
        if(game == null){
            return null;
        }

        GameEntity dbItem = repository.findById(game.getId()).orElse(null);
        if(dbItem == null){
            return null;
        }

        try {
            dbItem.setCoverArt(game.getCoverArt());
            return IGameEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
