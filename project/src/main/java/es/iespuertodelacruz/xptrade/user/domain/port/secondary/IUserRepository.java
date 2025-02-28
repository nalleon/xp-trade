package es.iespuertodelacruz.xptrade.user.domain.port.secondary;

import es.iespuertodelacruz.xptrade.user.domain.User;

import java.util.List;

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
