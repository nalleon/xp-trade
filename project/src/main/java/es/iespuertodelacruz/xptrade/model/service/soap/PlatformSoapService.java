package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Developer;
import es.iespuertodelacruz.xptrade.domain.Platform;
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
@WebService(
        serviceName = "PlatformSoapService",
        endpointInterface = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSoapService")
@Component
public class PlatformSoapService implements IGenericSoapService<Platform, Integer, String> {
    /**
     * Properties
     */
    private IGenericRepository<Platform, Integer, String> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGenericRepository<Platform, Integer, String> service) {
        this.service = service;
    }

    @Override
    public Platform save(Platform platform) {
        return service.save(platform);
    }

    @Override
    public List<Platform> findAll() {
        return service.findAll();
    }

    @Override
    public Platform findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Platform findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Platform update(Platform platform) {
        return service.update(platform);
    }
}
