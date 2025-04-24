package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.Role;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public interface IRoleRepository {
    Role save(Role role);
    List<Role> findAll();
    Role findById(Integer id);
    Role findByName(String name);
    boolean delete(Integer id);
    Role update(Role role);
}
