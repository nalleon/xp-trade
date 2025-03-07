package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.repository.IPlatformEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Platform save(Platform genre) {
        return null;
    }

    @Override
    public List<Platform> findAll() {
        return List.of();
    }

    @Override
    public Platform findById(Integer id) {
        return null;
    }

    @Override
    public Platform findByName(String name) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Platform update(Platform genre) {
        return null;
    }
}
