package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Role;
import es.iespuertodelacruz.xptrade.dto.input.RoleInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.RoleOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IRoleInputDTOMapper {
    IRoleInputDTOMapper INSTANCE = Mappers.getMapper(IRoleInputDTOMapper.class);
    Role toDomain(RoleInputDTO dto);
    RoleInputDTO toDTO(Role domain);
    List<Role> toDomainList(List<RoleInputDTO> dtos);
    List<RoleInputDTO> toDTOList(List<Role> domains);
}
