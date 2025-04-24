package es.iespuertodelacruz.xptrade.domain.interfaces.service;

import es.iespuertodelacruz.xptrade.domain.Role;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IRoleService {
    Role add(String name);
    Role findById(Integer id);
    Role findByName (String username);
    List<Role> findAll();
    boolean delete(Integer id);
    Role update(int id, String name);
}
