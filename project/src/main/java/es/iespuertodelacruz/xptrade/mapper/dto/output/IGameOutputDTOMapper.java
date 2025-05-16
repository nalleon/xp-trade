package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.dto.output.GameOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper(uses = {IPlatformOutputDTOMapper.class, IDeveloperOutputDTOMapper.class,
        IPublisherOutputDTOMapper.class, IGenreOutputDTOMapper.class})
public interface IGameOutputDTOMapper {
    IGameOutputDTOMapper INSTANCE = Mappers.getMapper(IGameOutputDTOMapper.class);
    @Mapping(target = "developerSet", source = "developerOutputDTOSet")
    @Mapping(target = "genreSet", source = "genreOutputDTOSet")
    @Mapping(target = "platformSet", source = "platformOutputDTOSet")
    @Mapping(target = "publisherSet", source = "publisherOutputDTOSet")
    Game toDomain(GameOutputDTO dto);

    @Mapping(target = "developerOutputDTOSet", source = "developerSet")
    @Mapping(target = "genreOutputDTOSet", source = "genreSet")
    @Mapping(target = "platformOutputDTOSet", source = "platformSet")
    @Mapping(target = "publisherOutputDTOSet", source = "publisherSet")
    GameOutputDTO toDTO(Game domain);

    List<Game> toDomainList(List<GameOutputDTO> dtos);
    List<GameOutputDTO> toDTOList(List<Game> domains);
}