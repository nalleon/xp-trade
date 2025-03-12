package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import es.iespuertodelacruz.xptrade.utilities.MapperHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRoleDTOMapperTest extends MapperDTOHelper {
    Role domainMapper;
    RoleDTO dtoMapper;
    List<RoleDTO> dtoListMapper;
    List<Role> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IRoleDTOMapper.INSTANCE.toDTOList(roleDomains);
        Assertions.assertEquals(roleDTOList, dtoListMapper, MESSAGE_ERROR);

        roleDomains = null;
        dtoListMapper = IRoleDTOMapper.INSTANCE.toDTOList(roleDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRoleDTOMapper.INSTANCE.toDomainList(roleDTOList);


        Assertions.assertEquals(roleDomains, domainListMapper, MESSAGE_ERROR);

        roleDTOList = null;
        domainListMapper = IRoleDTOMapper.INSTANCE.toDomainList(roleDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDTOTest(){
        dtoMapper = IRoleDTOMapper.INSTANCE.toDTO(roleDomain);

        Assertions.assertEquals(roleDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IRoleDTOMapper.INSTANCE.toDTO(roleDomain);

        roleDomain = null;
        dtoMapper = IRoleDTOMapper.INSTANCE.toDTO(roleDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IRoleDTOMapper.INSTANCE.toDomain(roleDTO);

        Assertions.assertEquals(roleDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(roleDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        roleDTO = null;
        domainMapper = IRoleDTOMapper.INSTANCE.toDomain(roleDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }


}
