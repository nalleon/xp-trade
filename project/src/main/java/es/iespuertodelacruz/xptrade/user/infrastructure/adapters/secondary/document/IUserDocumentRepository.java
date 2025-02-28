package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.secondary.document;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
@Repository
public interface IUserDocumentRepository extends MongoRepository<UserDocument, Integer> {
    @Query(
            value="SELECT * FROM usuarios WHERE nombre=:name",
            nativeQuery=true
    )
    Optional<UserDocument> findUserByName(@Param("name") String name);

    @Query(
            value="SELECT * FROM usuarios WHERE correo=:email",
            nativeQuery=true
    )
    Optional<UserDocument> findUserByEmail(@Param("email") String email);
    int deleteUserById(@Param("id") Integer id);

}
