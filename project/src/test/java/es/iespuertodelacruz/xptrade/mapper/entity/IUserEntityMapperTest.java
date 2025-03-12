package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IUserEntityMapperTest extends MapperHelper {
    User domainMapper;
    UserEntity entityMapper;
    List<UserEntity> entityListMapper;
    List<User> domainListMapper;

    @Test
    public void toEntityTest(){
        entityMapper = IUserEntityMapper.INSTANCE.toEntity(userDomain);

        Assertions.assertEquals(userEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getUsername(), entityMapper.getUsername(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getEmail(), entityMapper.getEmail(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getPassword(), entityMapper.getPassword(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getCreationDate(), entityMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getVerified(), entityMapper.getVerified(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getVerificationToken(), entityMapper.getVerificationToken(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getRole().getId(), entityMapper.getRole().getId(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getRole().getName(), entityMapper.getRole().getName(), MESSAGE_ERROR);
        Assertions.assertEquals(userEntity.getProfilePicture(), entityMapper.getProfilePicture(), MESSAGE_ERROR);

        entityMapper = IUserEntityMapper.INSTANCE.toEntity(userDomain);

        userDomain = null;
        entityMapper = IUserEntityMapper.INSTANCE.toEntity(userDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IUserEntityMapper.INSTANCE.toDomain(userEntity);

        Assertions.assertEquals(userDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getUsername(), domainMapper.getUsername(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getEmail(), domainMapper.getEmail(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getPassword(), domainMapper.getPassword(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getCreationDate(), domainMapper.getCreationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getVerified(), domainMapper.getVerified(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getVerificationToken(), domainMapper.getVerificationToken(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getRole(), domainMapper.getRole(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getRole().getName(), domainMapper.getRole().getName(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getRole().getId(), domainMapper.getRole().getId(), MESSAGE_ERROR);
        Assertions.assertEquals(userDomain.getProfilePicture(), domainMapper.getProfilePicture(), MESSAGE_ERROR);


        userEntity = null;
        domainMapper = IUserEntityMapper.INSTANCE.toDomain(userEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }

    @Test
    public void toEntityListTest(){
        entityListMapper = IUserEntityMapper.INSTANCE.toEntityList(userDomains);

        Assertions.assertEquals(userEntities, entityListMapper, MESSAGE_ERROR);

        userDomains = null;
        entityListMapper = IUserEntityMapper.INSTANCE.toEntityList(userDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);

    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IUserEntityMapper.INSTANCE.toDomainList(userEntities);


        Assertions.assertEquals(userDomains, domainListMapper, MESSAGE_ERROR);

        userEntities = null;
        domainListMapper = IUserEntityMapper.INSTANCE.toDomainList(userEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }
}
