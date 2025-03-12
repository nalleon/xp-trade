package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.IDeveloperEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.DeveloperEntity;
import es.iespuertodelacruz.xptrade.model.repository.IDeveloperEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class DeveloperEntityService implements IGenericRepository<Developer, Integer, String> {

    /**
     * Properties
     */
    IDeveloperEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IDeveloperEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Developer save(Developer platform) {
        if(platform == null){
            return null;
        }

        DeveloperEntity dbItem = repository.findByName(platform.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            DeveloperEntity entity = IDeveloperEntityMapper.INSTANCE.toEntity(platform);
            DeveloperEntity savedEntity = repository.save(entity);
            return IDeveloperEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Developer> findAll() {
        List<DeveloperEntity> listEntities = repository.findAll();
        return IDeveloperEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Developer findById(Integer id) {
        DeveloperEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IDeveloperEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Developer findByName(String name) {
        DeveloperEntity entityFound = repository.findByName(name).orElse(null);

        if (entityFound != null){
            return IDeveloperEntityMapper.INSTANCE.toDomain(entityFound);
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
    public Developer update(Developer platform) {
        if(platform == null ){
            return null;
        }

        DeveloperEntity dbItem = repository.findByName(platform.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(platform.getName());
            return IDeveloperEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}


