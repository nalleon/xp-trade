package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.IPostEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.PostEntity;
import es.iespuertodelacruz.xptrade.model.repository.IPostEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class PostEntityService  implements IGenericSocialRepository<Post, Integer, User, Game> {

    /**
     * Properties
     */
    IPostEntityRepository repository;

    /**
     * Setter for the autowired service
     *
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IPostEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Post save(Post post) {
        if (post == null) {
            return null;
        }

        try {
            PostEntity entity = IPostEntityMapper.INSTANCE.toEntity(post);
            entity.setCreationDate(new Date());
            PostEntity savedEntity = repository.save(entity);
            return IPostEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Post> findAll() {
        List<PostEntity> listEntities = repository.findAll();
        return IPostEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Post>  findAllLatest() {
        List<PostEntity> listEntities = repository.findAllLatest();
        return IPostEntityMapper.INSTANCE.toDomainList(listEntities);
    }


    @Override
    public Post findById(Integer id) {
        PostEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null) {
            return IPostEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public List<Post> findAllByUser(User user) {
        if(user == null){
            return null;
        }
        List<PostEntity> listEntities = repository.findAllByUser(user.getId());
        return IPostEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Post> findAllBySubject(Game game) {
        if(game == null){
            return null;
        }
        List<PostEntity> listEntities = repository.findAllByGame(game.getId());
        return IPostEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Post update(Post post) {
        if (post == null) {
            return null;
        }
        PostEntity dbItem = repository.findById(post.getId()).orElse(null);

        if (dbItem == null) {
            return null;
        }
        try {
            dbItem.setContent(post.getContent());
            dbItem.setPicture(post.getPicture());
            return IPostEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
