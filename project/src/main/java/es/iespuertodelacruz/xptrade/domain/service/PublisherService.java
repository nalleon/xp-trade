package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PublisherService implements IGenericService<Publisher, Integer, String> {

    /**
     * Properties
     */
    IGenericRepository<Publisher, Integer, String> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericRepository<Publisher, Integer, String> repository) {
        this.repository = repository;
    }

    @Override
    public Publisher add(String name) {
        Publisher aux = new Publisher(name);
        return repository.save(aux);
    }

    @Override
    public Publisher findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Publisher findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Publisher> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Publisher update(String name) {
        Publisher aux = new Publisher(name);
        return repository.update(aux);
    }
}
