package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.GameCollection;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGameCollectionRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.ICollectionEntityMapper;
import es.iespuertodelacruz.xptrade.mapper.entity.IGameCollectionEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import es.iespuertodelacruz.xptrade.model.entities.GameCollectionEntity;
import es.iespuertodelacruz.xptrade.model.repository.IGameCollectionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class GameCollectionEntityService implements IGameCollectionRepository {

    /**
     * Properties
     */
    private IGameCollectionEntityRepository repository;

    @Autowired
    public void setRepository(IGameCollectionEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public GameCollection save(GameCollection gameCollection) {
        if (gameCollection == null) {
            return null;
        }

        try {
            GameCollectionEntity entity = IGameCollectionEntityMapper.INSTANCE.toEntity(gameCollection);
            GameCollectionEntity savedEntity = repository.save(entity);
            return IGameCollectionEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<GameCollection> findAll() {
        List<GameCollectionEntity> listEntities = repository.findAll();
        return IGameCollectionEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public GameCollection findById(Integer id) {
        GameCollectionEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null) {
            return IGameCollectionEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    public GameCollection update(GameCollection gameCollection) {
        if (gameCollection == null) {
            return null;
        }
        GameCollectionEntity dbItem = repository.findById(gameCollection.getId()).orElse(null);

        if (dbItem == null) {
            return null;
        }

        try {
            GameCollectionEntity aux = IGameCollectionEntityMapper.INSTANCE.toEntity(gameCollection);
            dbItem.setPlatform(aux.getPlatform());
            dbItem.setRegion(aux.getRegion());
            return IGameCollectionEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
