package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.mapper.entity.IPlatformEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.PlatformEntity;
import es.iespuertodelacruz.xptrade.model.repository.IPlatformEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Service
public class PlatformEntityService implements IGenericRepository<Platform, Integer, String> {

    /**
     * Properties
     */
    IPlatformEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IPlatformEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Platform save(Platform platform) {
        if(platform == null){
            return null;
        }

        PlatformEntity dbItem = repository.findByName(platform.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            PlatformEntity entity = IPlatformEntityMapper.INSTANCE.toEntity(platform);
            PlatformEntity savedEntity = repository.save(entity);
            return IPlatformEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Platform> findAll() {
        List<PlatformEntity> listEntities = repository.findAll();
        return IPlatformEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Platform findById(Integer id) {
        PlatformEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IPlatformEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Platform findByName(String name) {
        PlatformEntity entityFound = repository.findByName(name).orElse(null);

        if (entityFound != null){
            return IPlatformEntityMapper.INSTANCE.toDomain(entityFound);
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
    public Platform update(Platform platform) {
        if(platform == null ){
            return null;
        }

        PlatformEntity dbItem = repository.findByName(platform.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(platform.getName());
            return IPlatformEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
