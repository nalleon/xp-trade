package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.dto.input.GameInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IPlatformInputDTOMapper.class, IDeveloperInputDTOMapper.class,
        IPublisherInputDTOMapper.class, IGenreInputDTOMapper.class})
public interface IGameInputDTOMapper {
    IGameInputDTOMapper INSTANCE = Mappers.getMapper(IGameInputDTOMapper.class);
    @Mapping(target = "developerSet", source = "developerInputDTOSet")
    @Mapping(target = "genreSet", source = "genreInputDTOSet")
    @Mapping(target = "platformSet", source = "platformInputDTOSet")
    @Mapping(target = "publisherSet", source = "publisherInputDTOSet")
    Game toDomain(GameInputDTO dto);

    @Mapping(target = "developerInputDTOSet", source = "developerSet")
    @Mapping(target = "genreInputDTOSet", source = "genreSet")
    @Mapping(target = "platformInputDTOSet", source = "platformSet")
    @Mapping(target = "publisherInputDTOSet", source = "publisherSet")
    GameInputDTO toDTO(Game domain);

    List<Game> toDomainList(List<GameInputDTO> dtos);
    List<GameInputDTO> toDTOList(List<Game> domains);
}