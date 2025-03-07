package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.mapper.IPublisherEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.PublisherEntity;
import es.iespuertodelacruz.xptrade.model.repository.IPublisherEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class PublisherEntityService implements IGenericRepository<Publisher, Integer, String> {

    /**
     * Properties
     */
    IPublisherEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IPublisherEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Publisher save(Publisher publisher) {
        if(publisher == null){
            return null;
        }

        PublisherEntity dbItem = repository.findByName(publisher.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            PublisherEntity entity = IPublisherEntityMapper.INSTANCE.toEntity(publisher);
            PublisherEntity savedEntity = repository.save(entity);
            return IPublisherEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Publisher> findAll() {
        List<PublisherEntity> listEntities = repository.findAll();
        return IPublisherEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Publisher findById(Integer id) {
        PublisherEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IPublisherEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Publisher findByName(String name) {
        PublisherEntity entityFound = repository.findByName(name).orElse(null);

        if (entityFound != null){
            return IPublisherEntityMapper.INSTANCE.toDomain(entityFound);
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
    public Publisher update(Publisher publisher) {
        if(publisher == null ){
            return null;
        }

        PublisherEntity dbItem = repository.findByName(publisher.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(publisher.getName());
            return IPublisherEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
