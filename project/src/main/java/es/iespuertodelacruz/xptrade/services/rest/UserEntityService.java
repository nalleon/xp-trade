package es.iespuertodelacruz.xptrade.services.rest;

import es.iespuertodelacruz.xptrade.mapper.IUserEntityMapper;
import es.iespuertodelacruz.xptrade.repository.IUserEntityRepository;
import es.iespuertodelacruz.xptrade.user.domain.User;
import es.iespuertodelacruz.xptrade.user.domain.port.secondary.IUserRepository;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
//@Service
public class UserEntityService implements IUserRepository {

    @Autowired
    private IUserEntityRepository repository;

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
            entity.setRoleId(2);
            entity.setVerificationToken(UUID.randomUUID().toString());
            entity.setVerified(0);
            UserEntity savedEntity = repository.save(entity);
            return IUserEntityMapper.INSTANCE.toDomain(savedEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data");
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
