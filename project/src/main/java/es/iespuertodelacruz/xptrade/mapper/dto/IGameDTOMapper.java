package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.dto.GameDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IPlatformDTOMapper.class, IDeveloperDTOMapper.class, IRegionDTOMapper.class,
        IPublisherDTOMapper.class, IGenreDTOMapper.class})
public interface IGameDTOMapper {
    IGameDTOMapper INSTANCE = Mappers.getMapper(IGameDTOMapper.class);
    @Mapping(target = "developerSet", source = "developerDTOSet")
    @Mapping(target = "genreSet", source = "genreDTOSet")
    @Mapping(target = "platformSet", source = "platformDTOSet")
    @Mapping(target = "publisherSet", source = "publisherDTOSet")
    @Mapping(target = "regionSet", source = "regionDTOSet")
    Game toDomain(GameDTO dto);

    @Mapping(target = "developerDTOSet", source = "developerSet")
    @Mapping(target = "genreDTOSet", source = "genreSet")
    @Mapping(target = "platformDTOSet", source = "platformSet")
    @Mapping(target = "publisherDTOSet", source = "publisherSet")
    @Mapping(target = "regionDTOSet", source = "regionSet")
    GameDTO toDTO(Game domain);

    List<Game> toDomainList(List<GameDTO> dtos);
    List<GameDTO> toDTOList(List<Game> domains);
}