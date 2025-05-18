package es.iespuertodelacruz.xptrade.mapper.dto.input;

import es.iespuertodelacruz.xptrade.domain.Tag;
import es.iespuertodelacruz.xptrade.dto.input.TagInputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Mapper
public interface ITagInputDTOMapper {
    ITagInputDTOMapper INSTANCE = Mappers.getMapper(ITagInputDTOMapper.class);
    Tag toDomain(TagInputDTO dto);
    TagInputDTO toDTO(Tag domain);
    List<Tag> toDomainList(List<TagInputDTO> dtos);
    List<TagInputDTO> toDTOList(List<Tag> domains);
}
