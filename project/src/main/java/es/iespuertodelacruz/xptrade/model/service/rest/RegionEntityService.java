package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Region;

import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.mapper.IRegionEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.RegionEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRegionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class RegionEntityService implements IGenericRepository<Region, Integer, String> {

    /**
     * Properties
     */
    IRegionEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IRegionEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Region save(Region region) {
        if(region == null){
            return null;
        }

        RegionEntity dbItem = repository.findByName(region.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            RegionEntity entity = IRegionEntityMapper.INSTANCE.toEntity(region);
            RegionEntity savedEntity = repository.save(entity);
            return IRegionEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Region> findAll() {
        List<RegionEntity> listEntities = repository.findAll();
        return IRegionEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Region findById(Integer id) {
        RegionEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IRegionEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Region findByName(String name) {
        RegionEntity entityFound = repository.findByName(name).orElse(null);

        if (entityFound != null){
            return IRegionEntityMapper.INSTANCE.toDomain(entityFound);
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
    public Region update(Region region) {
        if(region == null ){
            return null;
        }

        RegionEntity dbItem = repository.findByName(region.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(region.getName());
            return IRegionEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
