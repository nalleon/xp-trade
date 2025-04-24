package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper
public interface IRoleOutputDTOMapper {
    IRoleOutputDTOMapper INSTANCE = Mappers.getMapper(IRoleOutputDTOMapper.class);
    Role toDomain(RoleOutputDTO dto);
    RoleOutputDTO toDTO(Role domain);
    List<Role> toDomainList(List<RoleOutputDTO> dtos);
    List<RoleOutputDTO> toDTOList(List<Role> domains);
}
