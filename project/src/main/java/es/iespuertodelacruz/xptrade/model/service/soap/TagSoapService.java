package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Tag;
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
public class TagSoapService implements IGenericSoapService<Tag, Integer, String> {
    /**
     * Properties
     */
    private IGenericRepository<Tag, Integer, String> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService( IGenericRepository<Tag, Integer, String> service) {
        this.service = service;
    }

    @Override
    public Tag save(Tag tag) {
        return service.save(tag);
    }

    @Override
    public List<Tag> findAll() {
        return service.findAll();
    }

    @Override
    public Tag findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Tag findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Tag update(Tag tag) {
        return service.update(tag);
    }
}
