package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class DeveloperService implements IGenericService<Developer, Integer, String> {

    /**
     * Properties
     */
    IGenericRepository<Developer, Integer, String> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericRepository<Developer, Integer, String> repository) {
        this.repository = repository;
    }

    @Override
    public Developer add(String name) {
        Developer aux = new Developer(name);
        return repository.save(aux);
    }

    @Override
    public Developer findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Developer findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Developer> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Developer update(String name) {
        Developer aux = new Developer(name);
        return repository.update(aux);
    }
}
