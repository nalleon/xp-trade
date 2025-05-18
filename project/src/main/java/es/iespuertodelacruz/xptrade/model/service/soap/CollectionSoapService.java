package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Collection;
import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.ICollectionRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IGenericSocialRepository;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IUserRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.ICollectionSoapService;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSoapService;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSocialSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces.IGenericSocialSoapService")
@Component
public class CollectionSoapService implements ICollectionSoapService {
    /**
     * Properties
     */
    private ICollectionRepository service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(ICollectionRepository service) {
        this.service = service;
    }

    @Override
    public Collection save(Collection collection) {
        return service.save(collection);
    }

    @Override
    public List<Collection> findAll() {
        return service.findAll();
    }

    @Override
    public Collection findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<Collection> findAllByUser(User user) {
        return service.findAllByUser(user);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Collection update(Collection collection) {
        return service.update(collection);
    }
}
