package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Region;
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
public class RegionService implements IGenericService<Region, Integer, String> {

    /**
     * Properties
     */
    IGenericRepository<Region, Integer, String> repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IGenericRepository<Region, Integer, String> repository) {
        this.repository = repository;
    }

    @Override
    public Region add(String name) {
        Region aux = new Region(name);
        return repository.save(aux);
    }

    @Override
    public Region findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Region findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Region> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Region update(String name) {
        Region aux = new Region(name);
        return repository.update(aux);
    }
}
