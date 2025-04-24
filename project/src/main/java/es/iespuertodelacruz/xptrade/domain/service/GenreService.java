package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IGenericService;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Service
public class GenreService implements IGenericService<Genre, Integer, String> {

    /**
     * Properties
     */
    IGenericRepository<Genre, Integer, String> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericRepository<Genre, Integer, String> repository) {
        this.repository = repository;
    }
    
    @Override
    public Genre add(String name) {
        Genre aux = new Genre(name);
        return repository.save(aux);
    }

    @Override
    public Genre findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Genre findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Genre update(Integer id, String name) {
        Genre aux = new Genre(name);
        aux.setId(id);
        return repository.update(aux);
    }
}
