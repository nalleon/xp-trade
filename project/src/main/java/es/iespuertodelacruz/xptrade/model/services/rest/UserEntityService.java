package es.iespuertodelacruz.xptrade.model.services.rest;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.mapper.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.IUserRepository;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Service
public class UserEntityService implements IUserRepository {

    /**
     * Properties
     */
    private IUserEntityRepository repository;
    private IRoleEntityRepository roleRepository;
    /**
     * Setter for the autowired service
     * @param repository of the service
     */
    @Autowired
    public void setRepository(IUserEntityRepository repository) {
        this.repository = repository;
    }
    /**
     * Setter for the autowired service
     * @param roleRepository of the service
     */
    @Autowired
    public void setRoleRepository(IRoleEntityRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        if(user == null){
            return null;
        }

        UserEntity dbItem = repository.findUserByName(user.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            UserEntity entity = IUserEntityMapper.INSTANCE.toEntity(user);
            entity.setCreationDate(new Date());
            entity.setRole(getRoleUser());
            entity.setVerificationToken(UUID.randomUUID().toString());
            entity.setVerified(0);
            UserEntity savedEntity = repository.save(entity);
            return IUserEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data");
        }
    }

    public RoleEntity getRoleUser(){
        RoleEntity role =  roleRepository.findRoleByName("ROLE_USER").orElse(null);
        if (role != null){
            return role;
        } else {
            throw new RuntimeException("Role does not exists");

        }
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> listEntities = repository.findAll();
        return IUserEntityMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public User findById(Integer id) {
        UserEntity entityFound = repository.findById(id).orElse(null);

        if (entityFound != null){
            return IUserEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return  null;
    }

    @Override
    public User findByUserame(String username) {
        UserEntity entityFound = repository.findUserByName(username).orElse(null);

        if (entityFound != null){
            return IUserEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        UserEntity entityFound = repository.findUserByEmail(email).orElse(null);

        if (entityFound != null){
            return IUserEntityMapper.INSTANCE.toDomain(entityFound);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        int quantity = repository.deleteUserById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public User update(User user) {
        if(user == null ){
            return null;
        }

        UserEntity dbItem = repository.findUserByName(user.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setPassword(user.getPassword());
            dbItem.setEmail(user.getEmail());
            if(user.getVerified() != 0){
                dbItem.setVerified(1);
            }

            return IUserEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data");
        }

    }

    @Override
    @Transactional
    public User updatePicture(User user) {
        if(user == null ){
            return null;
        }

        UserEntity dbItem = repository.findUserByName(user.getName()).orElse(null);

        System.out.println(dbItem);

        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setProfilePicture(user.getProfilePicture());
            UserEntity result = repository.save(dbItem);
            return IUserEntityMapper.INSTANCE.toDomain(result);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data");
        }

    }
}
