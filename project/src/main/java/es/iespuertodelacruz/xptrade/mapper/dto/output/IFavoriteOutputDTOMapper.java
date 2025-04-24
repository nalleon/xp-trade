package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.dto.output.FavoriteOutputDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Mapper(uses = {IUserDTOMapper.class, IGameOutputDTOMapper.class})
public interface IFavoriteOutputDTOMapper {
    IFavoriteOutputDTOMapper INSTANCE = Mappers.getMapper(IFavoriteOutputDTOMapper.class);
    Favorite toDomain(FavoriteOutputDTO dto);
    FavoriteOutputDTO toDTO(Favorite domain);
    List<Favorite> toDomainList(List<FavoriteOutputDTO> dtos);
    List<FavoriteOutputDTO> toDTOList(List<Favorite> domains);


}