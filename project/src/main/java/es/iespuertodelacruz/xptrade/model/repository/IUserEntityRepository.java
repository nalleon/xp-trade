package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM users AS u WHERE u.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);


    @Query(
            value="SELECT * FROM users WHERE username=:username",
            nativeQuery=true
    )
    Optional<UserEntity> findUserByName(@Param("username") String name);


    @Query(
            value="SELECT * FROM users WHERE email=:email",
            nativeQuery=true
    )
    Optional<UserEntity> findUserByEmail(@Param("email") String email);
}
