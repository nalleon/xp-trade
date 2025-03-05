package es.iespuertodelacruz.xptrade.domain.interfaces;

import es.iespuertodelacruz.xptrade.domain.User;

import java.util.List;

public interface IUserService {
    User add(String name, String email, String password);
    User findById(Integer id);
    User findByEmail (String email);

    User findByUsername (String username);
    List<User> findAll();
    boolean delete(Integer id);
    User update(String name, String email, String password);
    User updateVerify(String name, String email, String password, int verify);
    User updatePicture(String name, String email, String password, String picture);
}
