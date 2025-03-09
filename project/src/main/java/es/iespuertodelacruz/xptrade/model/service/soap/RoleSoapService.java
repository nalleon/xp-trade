package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IRoleRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IRoleSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Component
@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces.IRoleSoapService")
public class RoleSoapService implements IRoleSoapService {
    /**
     * Properties
     */
    private IRoleRepository service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IRoleRepository service) {
        this.service = service;
    }

    @Override
    public Role save(Role role) {
        return service.save(role);
    }

    @Override
    public List<Role> findAll() {
        return service.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return service.findByName(name);
    }


    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public Role update(Role role) {
        return service.update(role);
    }

}
