package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.mapper.ICollectionEntityMapper;
import es.iespuertodelacruz.xptrade.mapper.IGameEntityMapper;
import es.iespuertodelacruz.xptrade.mapper.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import es.iespuertodelacruz.xptrade.model.repository.ICollectionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class CollectionEntiyService implements IGenericSocialRepository<Collection, Integer, User, Game> {

    /**
     * Properties
     */
    ICollectionEntityRepository repository;

    /**
     * Setter for the autowired service
     *
     * @param repository of the service
     */
    @Autowired
    public void setRepository(ICollectionEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Collection save(Collection favorite) {
        if (favorite == null) {
            return null;
        }

        try {
            CollectionEntity entity = ICollectionEntityMapper.INSTANCE.toEntity(favorite);
            CollectionEntity savedEntity = repository.save(entity);
            return ICollectionEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Collection> findAll() {
        List<CollectionEntity> listEntities = repository.findAll();
        return ICollectionEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Collection findById(Integer id) {
        CollectionEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null) {
            return ICollectionEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public List<Collection> findAllByUser(User user) {
        List<CollectionEntity> listEntities = repository.findAllByUser(user.getId());
        return ICollectionEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Collection> findAllBySubject(Game favorite) {
        List<CollectionEntity> listEntities = repository.findAllByGame(favorite.getId());
        return ICollectionEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Collection update(Collection favorite) {
        if (favorite == null) {
            return null;
        }
        CollectionEntity dbItem = repository.findById(favorite.getId()).orElse(null);

        if (dbItem == null) {
            return null;
        }
        try {
            dbItem.setGame(IGameEntityMapper.INSTANCE.toEntity(favorite.getGame()));
            dbItem.setUser(IUserEntityMapper.INSTANCE.toEntity(favorite.getUser()));
            return ICollectionEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}

