package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlatformService implements IGenericService<Platform, Integer, String> {

    /**
     * Properties
     */
    IGenericRepository<Platform, Integer, String> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericRepository<Platform, Integer, String> repository) {
        this.repository = repository;
    }

    @Override
    public Platform add(String name) {
        Platform aux = new Platform(name);
        return repository.save(aux);
    }

    @Override
    public Platform findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Platform findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Platform> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Platform update(String name) {
        Platform aux = new Platform(name);
        return repository.update(aux);
    }
}
