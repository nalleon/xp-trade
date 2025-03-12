package es.iespuertodelacruz.xptrade.mapper.entity;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRoleEntityMapperTest extends MapperHelper {
    Role domainMapper;
    RoleEntity entityMapper;
    List<RoleEntity> entityListMapper;
    List<Role> domainListMapper;

    @Test
    public void toEntityListTest(){
        entityListMapper = IRoleEntityMapper.INSTANCE.toEntityList(roleDomains);
        Assertions.assertEquals(roleEntities, entityListMapper, MESSAGE_ERROR);

        roleDomains = null;
        entityListMapper = IRoleEntityMapper.INSTANCE.toEntityList(roleDomains);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRoleEntityMapper.INSTANCE.toDomainList(roleEntities);


        Assertions.assertEquals(roleDomains, domainListMapper, MESSAGE_ERROR);

        roleEntities = null;
        domainListMapper = IRoleEntityMapper.INSTANCE.toDomainList(roleEntities);
        Assertions.assertNull(entityListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDTOTest(){
        entityMapper = IRoleEntityMapper.INSTANCE.toEntity(roleDomain);

        Assertions.assertEquals(roleEntity.getId(), entityMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(roleEntity.getName(), entityMapper.getName(), MESSAGE_ERROR);

        entityMapper = IRoleEntityMapper.INSTANCE.toEntity(roleDomain);

        roleDomain = null;
        entityMapper = IRoleEntityMapper.INSTANCE.toEntity(roleDomain);
        Assertions.assertNull(entityMapper, MESSAGE_ERROR);
    }

    @Test
    public void toEntityTest(){
        domainMapper = IRoleEntityMapper.INSTANCE.toDomain(roleEntity);

        Assertions.assertEquals(roleDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(roleDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        roleEntity = null;
        domainMapper = IRoleEntityMapper.INSTANCE.toDomain(roleEntity);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }


}
