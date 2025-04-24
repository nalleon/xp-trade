package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IUserRepository {
    User save(User user);
    List<User> findAll();
    User findById(Integer id);
    User findByUserame(String username);
    User findByEmail(String email);
    boolean delete(Integer id);
    User update(User user);
    User updatePicture(User user);
}
