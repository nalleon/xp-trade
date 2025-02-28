package es.iespuertodelacruz.xptrade.services.interfaces;

import es.iespuertodelacruz.xptrade.user.domain.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    User findById(Integer id);
    User findByUserame(String username);
    User findByEmail(String email);
    boolean delete(Integer id);
    User update(User user);
    User updatePicture(User user);
}
