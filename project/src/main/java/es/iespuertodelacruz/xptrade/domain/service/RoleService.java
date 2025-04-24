package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IRoleService;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Service
public class RoleService implements IRoleService {


    /**
     * Properties
     */
    IRoleRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IRoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public Role add(String name) {
        Role aux = new Role(name);
        return repository.save(aux);
    }

    @Override
    public Role findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public Role update(int id, String name) {
        Role aux = new Role(id, name);
        return repository.update(aux);
    }
}
