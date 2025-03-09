package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.Platform;
import es.iespuertodelacruz.xptrade.domain.Publisher;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSoapService")
@Component
public class PublisherSoapService implements IGenericSoapService<Publisher, Integer, String> {
    /**
     * Properties
     */
    private IGenericRepository<Publisher, Integer, String> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGenericRepository<Publisher, Integer, String> service) {
        this.service = service;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return service.save(publisher);
    }

    @Override
    public List<Publisher> findAll() {
        return service.findAll();
    }

    @Override
    public Publisher findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Publisher findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Publisher update(Publisher publisher) {
        return service.update(publisher);
    }
}
