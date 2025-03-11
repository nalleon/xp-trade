package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IRoleRepository;
import es.iespuertodelacruz.xptrade.mapper.IRoleEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.shared.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class RoleEntityService implements IRoleRepository {

    /**
     * Properties
     */
    IRoleEntityRepository repository;

    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IRoleEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Role save(Role role) {
        if(role == null){
            return null;
        }

        RoleEntity dbItem = repository.findRoleByName(role.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            RoleEntity entity = IRoleEntityMapper.INSTANCE.toEntity(role);
            RoleEntity savedEntity = repository.save(entity);
            return IRoleEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> listEntities = repository.findAll();
        return IRoleEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public Role findById(Integer id) {
        RoleEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IRoleEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public Role findByName(String name) {
        RoleEntity entityFound = repository.findRoleByName(name).orElse(null);

        if (entityFound != null){
            return IRoleEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        RoleEntity aux = repository.findById(id).orElse(null);
        if(aux != null && aux.getName().equals(Globals.ADMIN)){
            return false;
        }

        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public Role update(Role role) {
        if(role == null ){
            return null;
        }

        RoleEntity dbItem = repository.findRoleByName(role.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setName(role.getName());
            return IRoleEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }
    }
}
