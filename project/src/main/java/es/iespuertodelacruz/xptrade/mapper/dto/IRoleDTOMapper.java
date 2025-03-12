package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IRoleDTOMapper {
    IRoleDTOMapper INSTANCE = Mappers.getMapper(IRoleDTOMapper.class);
    Role toDomain(RoleDTO dto);
    RoleDTO toDTO(Role domain);
    List<Role> toDomainList(List<RoleDTO> dtos);
    List<RoleDTO> toDTOList(List<Role> domains);


}
