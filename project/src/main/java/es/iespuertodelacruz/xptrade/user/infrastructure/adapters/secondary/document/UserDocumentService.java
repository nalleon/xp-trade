package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.secondary.document;

import es.iespuertodelacruz.xptrade.user.domain.User;
import es.iespuertodelacruz.xptrade.user.domain.port.secondary.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
@Service
public class UserDocumentService implements IUserRepository {
    @Autowired
    private IUserDocumentRepository repository;
    @Override
    @Transactional
    public User save(User user) {
        if(user == null){
            return null;
        }

        UserDocument dbItem = repository.findUserByName(user.getName()).orElse(null);

        if(dbItem != null){
            return null;
        }

        try {
            UserDocument document = IUserDocumentMapper.INSTANCE.toDocument(user);
            document.setCreationDate(new Date());
            document.setRole("ROLE_USER");
            document.setVerificationToken(UUID.randomUUID().toString());
            document.setVerified(0);
            UserDocument saved = repository.save(document);
            return IUserDocumentMapper.INSTANCE.toDomain(saved);
        } catch (RuntimeException e){
            throw new RuntimeException("Invalid data");
        }
    }

    @Override
    public List<User> findAll() {
        List<UserDocument> listEntities = repository.findAll();
        return IUserDocumentMapper.INSTANCE.toDomainList(listEntities);
    }

    @Override
    public User findById(Integer id) {
        UserDocument documentFound = repository.findById(id).orElse(null);

        if (documentFound != null){
            return IUserDocumentMapper.INSTANCE.toDomain(documentFound);
        }
        return  null;
    }

    @Override
    public User findByUserame(String username) {
        UserDocument documentFound = repository.findUserByName(username).orElse(null);

        if (documentFound != null){
            return IUserDocumentMapper.INSTANCE.toDomain(documentFound);
        }

        return null;
    }

    @Override
    public User findByEmail(String email) {
        UserDocument documentFound = repository.findUserByEmail(email).orElse(null);
        System.out.println("__________________________________");
        System.out.println(documentFound);
        System.out.println("__________________________________");

        if (documentFound != null){
            System.out.println("MAPPER: " + IUserDocumentMapper.INSTANCE.toDomain(documentFound));

            return IUserDocumentMapper.INSTANCE.toDomain(documentFound);
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

        UserDocument dbItem = repository.findUserByName(user.getName()).orElse(null);
        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setPassword(user.getPassword());
            dbItem.setEmail(user.getEmail());
            if(user.getVerified() != 0){
                dbItem.setVerified(1);
            }

            return IUserDocumentMapper.INSTANCE.toDomain(dbItem);
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

        UserDocument dbItem = repository.findUserByName(user.getName()).orElse(null);

        System.out.println(dbItem);

        if (dbItem == null){
            return null;
        }

        try {
            dbItem.setProfilePicture(user.getProfilePicture());
            UserDocument result = repository.save(dbItem);
            return IUserDocumentMapper.INSTANCE.toDomain(result);
        }  catch (RuntimeException e){
            throw new RuntimeException("Invalid data");
        }

    }
}
