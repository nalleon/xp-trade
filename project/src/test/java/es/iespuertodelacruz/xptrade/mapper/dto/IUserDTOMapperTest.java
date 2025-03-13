package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.User;

import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import es.iespuertodelacruz.xptrade.utilities.MapperDTOHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IUserDTOMapperTest extends MapperDTOHelper {
    User domainMapper;
    UserDTO dtoMapper;
    List<UserDTO> dtoListMapper;
    List<User> domainListMapper;

    @Test
    public void toDTOTest(){
        dtoMapper = IUserDTOMapper.INSTANCE.toDTO(userDomain);

        Assertions.assertEquals(userDTO.username(), dtoMapper.username(), MESSAGE_ERROR);

        Assertions.assertEquals(userDTO.id(), dtoMapper.id(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.username(), dtoMapper.username(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.email(), dtoMapper.email(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.password(), dtoMapper.password(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.creationDate(), dtoMapper.creationDate(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.verified(), dtoMapper.verified(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.verificationToken(), dtoMapper.verificationToken(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.role(), dtoMapper.role(), MESSAGE_ERROR);
        Assertions.assertEquals(userDTO.profilePicture(), dtoMapper.profilePicture(), MESSAGE_ERROR);

        userDomain = null;
        dtoMapper = IUserDTOMapper.INSTANCE.toDTO(userDomain);
        Assertions.assertNull(dtoMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainTest(){
        domainMapper = IUserDTOMapper.INSTANCE.toDomain(userDTO);

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


        userDTO = null;
        domainMapper = IUserDTOMapper.INSTANCE.toDomain(userDTO);
        Assertions.assertNull(domainMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDTOListTest(){
        dtoListMapper = IUserDTOMapper.INSTANCE.toDTOList(userDomains);

        Assertions.assertEquals(userDTOList, dtoListMapper, MESSAGE_ERROR);

        userDomains = null;
        dtoListMapper = IUserDTOMapper.INSTANCE.toDTOList(userDomains);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }

    @Test
    public void toDomainListTest(){
        domainListMapper = IUserDTOMapper.INSTANCE.toDomainList(userDTOList);

        Assertions.assertEquals(userDomains.get(0).getUsername(), domainListMapper.get(0).getUsername(), MESSAGE_ERROR);

        userDTOList = null;
        domainListMapper = IUserDTOMapper.INSTANCE.toDomainList(userDTOList);
        Assertions.assertNull(dtoListMapper, MESSAGE_ERROR);
    }
}
