package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Favorite;
import es.iespuertodelacruz.xptrade.dto.FavoriteDTO;
import es.iespuertodelacruz.xptrade.mapper.dto.user.IUserSearchDTOMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IUserSearchDTOMapper.class, IGameDTOMapper.class})
public interface IFavoriteDTOMapper {
    IFavoriteDTOMapper INSTANCE = Mappers.getMapper(IFavoriteDTOMapper.class);
    Favorite toDomain(FavoriteDTO dto);
    FavoriteDTO toDTO(Favorite domain);
    List<Favorite> toDomainList(List<FavoriteDTO> dtos);
    List<FavoriteDTO> toDTOList(List<Favorite> domains);


}