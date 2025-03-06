package es.iespuertodelacruz.xptrade.mapper;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static es.iespuertodelacruz.xptrade.utilities.TestUtilities.MESSAGE_ERROR;

public class IRoleEntityMapperTest extends MapperHelper {
    Role domainMapper;
    RoleEntity entityMapper;

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
