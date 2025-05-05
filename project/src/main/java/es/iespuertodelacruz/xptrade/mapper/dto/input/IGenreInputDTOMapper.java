package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.dto.input.GenreInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IGenreInputDTOMapper {
    IGenreInputDTOMapper INSTANCE = Mappers.getMapper(IGenreInputDTOMapper.class);
    Genre toDomain(GenreInputDTO dto);
    GenreInputDTO toDTO(Genre domain);
    List<Genre> toDomainList(List<GenreInputDTO> dtos);
    List<GenreInputDTO> toDTOList(List<Genre> domains);
}
