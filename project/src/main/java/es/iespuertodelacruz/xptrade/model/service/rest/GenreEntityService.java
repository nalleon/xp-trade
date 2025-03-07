package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.repository.IGenreEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class GenreEntityService implements IGenericRepository<Genre, Integer, String> {

    /**
     * Properties
     */
    IGenreEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenreEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre save(Genre genre) {
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return List.of();
    }

    @Override
    public Genre findById(Integer id) {
        return null;
    }

    @Override
    public Genre findByName(String name) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }
}
