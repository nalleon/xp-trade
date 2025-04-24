package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Comment;
import es.iespuertodelacruz.xptrade.domain.Post;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.ICommentEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.CommentEntity;
import es.iespuertodelacruz.xptrade.model.repository.ICommentEntityRepository;
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
public class CommentEntityService implements IGenericSocialRepository<Comment, Integer, User, Post> {

    /**
     * Properties
     */
    ICommentEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(ICommentEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Comment save(Comment comment) {
        if(comment == null){
            return null;
        }

        try {
            CommentEntity entity = ICommentEntityMapper.INSTANCE.toEntity(comment);
            entity.setCreationDate(new Date());
            CommentEntity savedEntity = repository.save(entity);
            return ICommentEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Comment> findAll() {
        List<CommentEntity> listEntities = repository.findAll();
        return ICommentEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Comment findById(Integer id) {
        CommentEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return ICommentEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public List<Comment> findAllByUser(User user) {
        if(user == null){
            return null;
        }
        List<CommentEntity> listEntities = repository.findAllByUser(user.getId());
        return ICommentEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public List<Comment> findAllBySubject(Post post) {
        if(post == null){
            return null;
        }
        List<CommentEntity> listEntities = repository.findAllByPost(post.getId());
        return ICommentEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        if(comment == null){
            return null;
        }
        CommentEntity dbItem = repository.findById(comment.getId()).orElse(null);

        if(dbItem == null){
            return null;
        }
        try {
            dbItem.setContent(comment.getContent());
            return ICommentEntityMapper.INSTANCE.toDomain(dbItem);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
