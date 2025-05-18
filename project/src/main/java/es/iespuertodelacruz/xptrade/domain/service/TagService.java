package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Tag;
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
public class TagService implements IGenericService<Tag, Integer, String> {

    /**
     * Properties
     */
    IGenericRepository<Tag, Integer, String> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericRepository<Tag, Integer, String> repository) {
        this.repository = repository;
    }
    
    @Override
    public Tag add(String name) {
        Tag aux = new Tag(name);
        return repository.save(aux);
    }

    @Override
    public Tag findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Tag findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Tag update(Integer id, String name) {
        Tag aux = new Tag(name);
        aux.setId(id);
        return repository.update(aux);
    }
}
