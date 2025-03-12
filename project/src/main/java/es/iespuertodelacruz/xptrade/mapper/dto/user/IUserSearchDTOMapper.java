package es.iespuertodelacruz.xptrade.mapper.dto.user;

import es.iespuertodelacruz.xptrade.domain.User;
import es.iespuertodelacruz.xptrade.dto.user.UserSearchDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.IRoleDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IRoleDTOMapper.class})
public interface IUserSearchDTOMapper {
    IUserSearchDTOMapper INSTANCE = Mappers.getMapper(IUserSearchDTOMapper.class);
    User toDomain(UserSearchDTO dto);
    UserSearchDTO toDTO(User domain);
    List<User> toDomainList(List<UserSearchDTO> dtos);
    List<UserSearchDTO> toDTOList(List<User> domains);
}