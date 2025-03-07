package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.repository.IPublisherEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class PublisherEntityService implements IGenericRepository<Publisher, Integer, String> {

    /**
     * Properties
     */
    IPublisherEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IPublisherEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Publisher save(Publisher genre) {
        return null;
    }

    @Override
    public List<Publisher> findAll() {
        return List.of();
    }

    @Override
    public Publisher findById(Integer id) {
        return null;
    }

    @Override
    public Publisher findByName(String name) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public Publisher update(Publisher genre) {
        return null;
    }
}
