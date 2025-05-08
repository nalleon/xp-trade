package es.iespuertodelacruz.xptrade.model.service.rest;

import es.iespuertodelacruz.xptrade.mapper.entity.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.repository.IRoleEntityRepository;
import es.iespuertodelacruz.xptrade.model.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.domain.interfaces.repository.IUserRepository;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.shared.utils.Globals;
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

        UserEntity dbItem = repository.findUserByName(user.getUsername()).orElse(null);

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
            throw new RuntimeException("Invalid data: " + e);
        }
    }

    public RoleEntity getRoleUser(){
        RoleEntity role =  roleRepository.findRoleByName("ROLE_USER").orElse(null);
        if (role == null) {
            role = roleRepository.save(new RoleEntity("ROLE_USER"));
        }
        return role;
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

        System.out.println("ORVITO =======================> " +entityFound);
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
        UserEntity aux = repository.findById(id).orElse(null);
        if(aux != null && aux.getRole().getName().equals(Globals.ADMIN)){
            return false;
        }

        int quantity = repository.deleteEntityById(id);
        return quantity > 0;
    }

    @Override
    @Transactional
    public User update(User user) {
        if(user == null ){
            return null;
        }

        UserEntity dbItem = repository.findUserByName(user.getUsername()).orElse(null);
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
            throw new RuntimeException("Invalid data: " + e);
        }

    }

    @Override
    @Transactional
    public User updatePicture(User user) {
        if(user == null ){
            return null;
        }

        UserEntity dbItem = repository.findUserByName(user.getUsername()).orElse(null);

        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setProfilePicture(user.getProfilePicture());
            return IUserEntityMapper.INSTANCE.toDomain(dbItem);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data: " + e);
        }

    }
}
