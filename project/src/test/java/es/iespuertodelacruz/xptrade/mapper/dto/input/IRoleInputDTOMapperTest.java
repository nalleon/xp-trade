package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.dto.input.RoleInputDTO;
import es.iespuertodelacruz.xptrade.utilities.MapperInputDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IRoleInputDTOMapperTest extends MapperInputDTOHelper {
    Role domainMapper;
    RoleInputDTO dtoMapper;
    List<RoleInputDTO> dtoListMapper;
    List<Role> domainListMapper;

    @Test
    public void toDTOListTest(){
        dtoListMapper = IRoleInputDTOMapper.INSTANCE.toDTOList(roleDomains);
        Assertions.assertEquals(roleInputDTOList, dtoListMapper, MESSAGE_ERROR);

        roleDomains = null;
        dtoListMapper = IRoleInputDTOMapper.INSTANCE.toDTOList(roleDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IRoleInputDTOMapper.INSTANCE.toDomainList(roleInputDTOList);


        Assertions.assertEquals(roleDomains, domainListMapper, MESSAGE_ERROR);

        roleInputDTOList = null;
        domainListMapper = IRoleInputDTOMapper.INSTANCE.toDomainList(roleInputDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDTOTest(){
        dtoMapper = IRoleInputDTOMapper.INSTANCE.toDTO(roleDomain);

        Assertions.assertEquals(roleInputDTO.name(), dtoMapper.name(), MESSAGE_ERROR);

        dtoMapper = IRoleInputDTOMapper.INSTANCE.toDTO(roleDomain);

        roleDomain = null;
        dtoMapper = IRoleInputDTOMapper.INSTANCE.toDTO(roleDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IRoleInputDTOMapper.INSTANCE.toDomain(roleInputDTO);

        Assertions.assertEquals(roleDomain.getName(), domainMapper.getName(), MESSAGE_ERROR);


        roleInputDTO = null;
        domainMapper = IRoleInputDTOMapper.INSTANCE.toDomain(roleInputDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }


}
