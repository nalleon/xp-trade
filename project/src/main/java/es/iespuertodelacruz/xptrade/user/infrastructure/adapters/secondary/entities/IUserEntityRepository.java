package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.secondary.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM usuarios AS u WHERE u.id=:id",
            nativeQuery=true
    )
    int deleteUserById(@Param("id") Integer id);


    @Query(
            value="SELECT * FROM usuarios WHERE nombre=:name",
            nativeQuery=true
    )
    Optional<UserEntity> findUserByName(@Param("name") String name);


    @Query(
            value="SELECT * FROM usuarios WHERE correo=:email",
            nativeQuery=true
    )
    Optional<UserEntity> findUserByEmail(@Param("email") String email);
}
