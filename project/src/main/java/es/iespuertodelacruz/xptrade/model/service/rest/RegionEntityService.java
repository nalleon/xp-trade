package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.repository.IRegionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Region save(Region genre) {
        return null;
    }

    @Override
    public List<Region> findAll() {
        return List.of();
    }

    @Override
    public Region findById(Integer id) {
        return null;
    }

    @Override
    public Region findByName(String name) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Region update(Region genre) {
        return null;
    }
}
