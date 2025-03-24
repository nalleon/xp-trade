package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Region;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IRegionSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@WebService(endpointInterface = "es.iespuertodelacruz.xptrade.model.service.interfaces.IRegionSoapService")
@Component
public class RegionSoapService implements IRegionSoapService {
    /**
     * Properties
     */
    private IGenericRepository<Region, Integer, String> service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IGenericRepository<Region, Integer, String> service) {
        this.service = service;
    }

    @Override
    public Region save(Region region) {
        return service.save(region);
    }

    @Override
    public List<Region> findAll() {
        return service.findAll();
    }

    @Override
    public Region findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Region findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Region update(Region region) {
        return service.update(region);
    }
}
