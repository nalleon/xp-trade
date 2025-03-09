package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
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
public class DeveloperSoapService implements IGenericSoapService<Developer, Integer, String> {
    /**
     * Properties
     */
    private IGenericRepository<Developer, Integer, String> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService( IGenericRepository<Developer, Integer, String> service) {
        this.service = service;
    }

    @Override
    public Developer save(Developer developer) {
        return service.save(developer);
    }

    @Override
    public List<Developer> findAll() {
        return service.findAll();
    }

    @Override
    public Developer findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Developer findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Developer update(Developer developer) {
        return service.update(developer);
    }
}
