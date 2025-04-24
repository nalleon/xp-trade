package es.iespuertodelacruz.xptrade.mapper.dto.user;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.dto.user.UserDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IRoleOutputDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper(uses = {IRoleOutputDTOMapper.class})
public interface IUserDTOMapper {
    IUserDTOMapper INSTANCE = Mappers.getMapper(IUserDTOMapper.class);
    @Mapping(target = "role", source = "role")
    User toDomain(UserDTO dto);
    @Mapping(target = "role", source = "role")
    UserDTO toDTO(User domain);
    List<User> toDomainList(List<UserDTO> dtos);
    List<UserDTO> toDTOList(List<User> domains);
}