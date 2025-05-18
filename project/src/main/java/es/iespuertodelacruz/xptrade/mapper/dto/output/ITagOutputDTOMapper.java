package es.iespuertodelacruz.xptrade.mapper.dto.output;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.dto.output.TagOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface ITagOutputDTOMapper {
    ITagOutputDTOMapper INSTANCE = Mappers.getMapper(ITagOutputDTOMapper.class);
    Tag toDomain(TagOutputDTO dto);
    TagOutputDTO toDTO(Tag domain);
    List<Tag> toDomainList(List<TagOutputDTO> dtos);
    List<TagOutputDTO> toDTOList(List<Tag> domains);
}
