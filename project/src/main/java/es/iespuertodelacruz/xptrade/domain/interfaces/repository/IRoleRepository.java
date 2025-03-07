package es.iespuertodelacruz.xptrade.domain.interfaces.repository;

import es.iespuertodelacruz.xptrade.domain.Role;

import java.util.List;

public interface IRoleRepository {
    Role save(Role role);
    List<Role> findAll();
    Role findById(Integer id);
    Role findByName(String name);
    boolean delete(Integer id);
    Role update(Role role);
}
