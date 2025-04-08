package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IRoleOutputDTOMapper;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRoleOutputDTOMapperTest extends MapperDTOHelper {
    Role domainMapper;
    RoleOutputDTO dtoMapper;
    List<RoleOutputDTO> dtoListMapper;
    List<Role> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IRoleOutputDTOMapper.INSTANCE.toDTOList(roleDomains);
        Assertions.assertEquals(roleOutputDTOList, dtoListMapper, MESSAGE_ERROR);

        roleDomains = null;
        dtoListMapper = IRoleOutputDTOMapper.INSTANCE.toDTOList(roleDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRoleOutputDTOMapper.INSTANCE.toDomainList(roleOutputDTOList);


        Assertions.assertEquals(roleDomains, domainListMapper, MESSAGE_ERROR);

        roleOutputDTOList = null;
        domainListMapper = IRoleOutputDTOMapper.INSTANCE.toDomainList(roleOutputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDTOTest(){
        dtoMapper = IRoleOutputDTOMapper.INSTANCE.toDTO(roleDomain);

        Assertions.assertEquals(roleOutputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IRoleOutputDTOMapper.INSTANCE.toDTO(roleDomain);

        roleDomain = null;
        dtoMapper = IRoleOutputDTOMapper.INSTANCE.toDTO(roleDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IRoleOutputDTOMapper.INSTANCE.toDomain(roleOutputDTO);

        Assertions.assertEquals(roleDomain.getId(), domainMapper.getId(), MESSAGE_ERROR);
        Assertions.assertEquals(roleDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        roleOutputDTO = null;
        domainMapper = IRoleOutputDTOMapper.INSTANCE.toDomain(roleOutputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }


}
