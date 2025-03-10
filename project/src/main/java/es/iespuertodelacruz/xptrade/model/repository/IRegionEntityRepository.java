package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.PublisherEntity;
import es.iespuertodelacruz.xptrade.model.entities.RegionEntity;
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
public interface IRegionEntityRepository extends JpaRepository<RegionEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM regions AS r WHERE r.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);


    @Query(
            value="SELECT * FROM regions WHERE username =:username",
            nativeQuery=true
    )
    Optional<RegionEntity> findByName(@Param("username") String name);
}