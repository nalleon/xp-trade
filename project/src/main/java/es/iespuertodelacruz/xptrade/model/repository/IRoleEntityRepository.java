package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.RoleEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Repository
public interface IRoleEntityRepository extends JpaRepository<RoleEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM roles AS r WHERE r.id=:id",
            nativeQuery=true
    )
    int deleteUserById(@Param("id") Integer id);


    @Query(
            value="SELECT * FROM roles WHERE name =:name",
            nativeQuery=true
    )
    Optional<RoleEntity> findRoleByName(@Param("name") String name);

}
