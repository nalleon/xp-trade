package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.repository.IDeveloperEntityRepository;
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
    public Developer save(Developer genre) {
        return null;
    }

    @Override
    public List<Developer> findAll() {
        return List.of();
    }

    @Override
    public Developer findById(Integer id) {
        return null;
    }

    @Override
    public Developer findByName(String name) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Developer update(Developer genre) {
        return null;
    }
}
