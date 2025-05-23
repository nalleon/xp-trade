package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.IFavoriteEntityMapper;
import es.iespuertodelacruz.xptrade.mapper.entity.IGameEntityMapper;
import es.iespuertodelacruz.xptrade.mapper.entity.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.FavoriteEntity;
import es.iespuertodelacruz.xptrade.model.repository.IFavoriteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class FavoriteEntityService implements IGenericSocialRepository<Favorite, Integer, User, Game> {

    /**
     * Properties
     */
    IFavoriteEntityRepository repository;

    /**
     * Setter for the autowired service
     *
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IFavoriteEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Favorite save(Favorite favorite) {
        if (favorite == null) {
            return null;
        }

        try {
            FavoriteEntity entity = IFavoriteEntityMapper.INSTANCE.toEntity(favorite);
            FavoriteEntity savedEntity = repository.save(entity);
            return IFavoriteEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Favorite> findAll() {
        List<FavoriteEntity> listEntities = repository.findAll();
        return IFavoriteEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Favorite findById(Integer id) {
        FavoriteEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null) {
            return IFavoriteEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public Favorite checkIfExists(User user, Game game) {
        FavoriteEntity entityFound = repository.checkIfInFavorite(user.getUsername(), game.getTitle()).orElse(null);

        if (entityFound != null) {
            return IFavoriteEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public List<Favorite> findAllByUser(User user) {
        if(user == null){
            return null;
        }
        List<FavoriteEntity> listEntities = repository.findAllByUser(user.getId());
        return IFavoriteEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Favorite> findAllBySubject(Game favorite) {
        if(favorite == null){
            return null;
        }
        List<FavoriteEntity> listEntities = repository.findAllByGame(favorite.getId());
        return IFavoriteEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Favorite update(Favorite favorite) {
        if (favorite == null) {
            return null;
        }
        FavoriteEntity dbItem = repository.findById(favorite.getId()).orElse(null);

        if (dbItem == null) {
            return null;
        }

        try {
            FavoriteEntity aux = IFavoriteEntityMapper.INSTANCE.toEntity(favorite);
            dbItem.setGame(aux.getGame());
            dbItem.setUser(aux.getUser());
            return IFavoriteEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
