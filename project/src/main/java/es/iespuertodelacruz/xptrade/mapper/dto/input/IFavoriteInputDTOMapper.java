package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.dto.input.FavoriteInputDTO;
import es.iespuertodelacruz.xptrade.dto.output.FavoriteOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.output.IGameOutputDTOMapper;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserDTOMapper.class, IGameInputDTOMapper.class})
public interface IFavoriteInputDTOMapper {
    IFavoriteInputDTOMapper INSTANCE = Mappers.getMapper(IFavoriteInputDTOMapper.class);
    Favorite toDomain(FavoriteInputDTO dto);
    FavoriteInputDTO toDTO(Favorite domain);
    List<Favorite> toDomainList(List<FavoriteInputDTO> dtos);
    List<FavoriteInputDTO> toDTOList(List<Favorite> domains);


}