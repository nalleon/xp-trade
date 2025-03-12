package es.iespuertodelacruz.xptrade.mapper.dto;

import es.iespuertodelacruz.xptrade.domain.Genre;
import es.iespuertodelacruz.xptrade.dto.GenreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface IGenreDTOMapper {
    IGenreDTOMapper INSTANCE = Mappers.getMapper(IGenreDTOMapper.class);
    Genre toDomain(GenreDTO dto);
    GenreDTO toDTO(Genre domain);
    List<Genre> toDomainList(List<GenreDTO> dtos);
    List<GenreDTO> toDTOList(List<Genre> domains);
}
