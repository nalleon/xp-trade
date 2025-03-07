package es.iespuertodelacruz.xptrade.domain.service;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.service.IUserService;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class UserService implements IUserService {

    /**
     * Properties
     */
    IUserRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User add(String name, String email, String password) {
        User aux = new User(name, password, email);
        return repository.save(aux);
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUserame(username);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public User update(String name, String email, String password) {
        User aux = new User(name, password, email);
        return repository.update(aux);
    }

    @Override
    public User updateVerify(String name, String email, String password, int verify) {
        User aux = new User(name, password, email, verify);
        return repository.update(aux);
    }

    @Override
    public User updatePicture(String name, String email, String password, String picture) {
        User aux = new User(name, email, password, picture);
        return repository.updatePicture(aux);
    }
}
