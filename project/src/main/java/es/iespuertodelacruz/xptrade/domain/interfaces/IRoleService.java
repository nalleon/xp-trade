package es.iespuertodelacruz.xptrade.domain.interfaces;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.Role;

import java.util.List;

public interface IRoleService {
    Role add(String name);
    Role findById(Integer id);
    Role findByName (String username);
    List<Role> findAll();
    boolean delete(Integer id);
    Role update(int id, String name);
}
