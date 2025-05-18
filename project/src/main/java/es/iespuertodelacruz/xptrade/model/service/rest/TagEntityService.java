package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.ITagEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.TagEntity;
import es.iespuertodelacruz.xptrade.model.repository.ITagEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class TagEntityService implements IGenericRepository<Tag, Integer, String> {

    /**
     * Properties
     */
    ITagEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(ITagEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Tag save(Tag tag) {
        if(tag == null){
            return null;
        }

        TagEntity dbItem = repository.findByName(tag.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            TagEntity entity = ITagEntityMapper.INSTANCE.toEntity(tag);
            TagEntity savedEntity = repository.save(entity);
            return ITagEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Tag> findAll() {
        List<TagEntity> listEntities = repository.findAll();
        return ITagEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Tag findById(Integer id) {
        TagEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return ITagEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Tag findByName(String name) {
        TagEntity entityFound = repository.findByName(name).orElse(null);

        if (entityFound != null){
            return ITagEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Tag update(Tag tag) {
        if(tag == null ){
            return null;
        }

        TagEntity dbItem = repository.findByName(tag.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(tag.getName());
            return ITagEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
