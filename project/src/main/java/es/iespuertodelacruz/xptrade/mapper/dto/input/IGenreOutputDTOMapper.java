package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.dto.output.GenreOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IGenreOutputDTOMapper {
    IGenreOutputDTOMapper INSTANCE = Mappers.getMapper(IGenreOutputDTOMapper.class);
    Genre toDomain(GenreOutputDTO dto);
    GenreOutputDTO toDTO(Genre domain);
    List<Genre> toDomainList(List<GenreOutputDTO> dtos);
    List<GenreOutputDTO> toDTOList(List<Genre> domains);
}
