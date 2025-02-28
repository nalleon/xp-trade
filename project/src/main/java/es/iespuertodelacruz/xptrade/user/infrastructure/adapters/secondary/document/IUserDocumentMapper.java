package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.secondary.document;

import es.iespuertodelacruz.xptrade.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
@Mapper
public interface IUserDocumentMapper {
    IUserDocumentMapper INSTANCE = Mappers.getMapper(IUserDocumentMapper.class);

    @Mapping(source="id", target = "id", ignore = true)
//    @Mapping(source = "verificationToken", target = "verificationToken", qualifiedByName = "mapToken")
    User toDomain(UserDocument document);

    @Mapping(source="id", target = "id", ignore = true)
//    @Mapping(source = "verificationToken", target = "verificationToken", ignore = false)
    UserDocument toDocument(User domain);
    List<User> toDomainList(List<UserDocument> documents);
    List<UserDocument> toDocumentList(List<User> domains);
}