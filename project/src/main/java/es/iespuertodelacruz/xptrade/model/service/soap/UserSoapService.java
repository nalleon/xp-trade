package es.iespuertodelacruz.xptrade.model.service.soap;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IUserRepository;
import es.iespuertodelacruz.xptrade.model.service.interfaces.IUserSoapService;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Component
@WebService(targetNamespace = "es.iespuertodelacruz.xptrade.model.service.interfaces.IUserSoapService")
public class UserSoapService implements IUserSoapService {

    /**
     * Properties
     */
    private IUserRepository service;

    /**
     * Setter of the service
     * @param service restfull
     */
    @Autowired
    public void setService(IUserRepository service) {
        this.service = service;
    }

    @Override
    public User save(User user) {
        return service.save(user);
    }

    @Override
    public List<User> findAll() {
        return service.findAll();
    }

    @Override
    public User findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public User findByUserame(String username) {
        return service.findByUserame(username);
    }

    @Override
    public User findByEmail(String email) {
        return service.findByEmail(email);
    }

    @Override
    public boolean delete(Integer id) {
        return service.delete(id);
    }

    @Override
    public User update(User user) {
        return service.update(user);
    }

    @Override
    public User updatePicture(User user) {
        return service.update(user);
    }
}
