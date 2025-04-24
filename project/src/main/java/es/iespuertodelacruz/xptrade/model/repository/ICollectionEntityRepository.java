package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import es.iespuertodelacruz.xptrade.model.entities.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Repository
public interface ICollectionEntityRepository extends JpaRepository<CollectionEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM collections AS r WHERE r.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);


    @Query(
            value="SELECT * FROM collections WHERE user_id =:user_id",
            nativeQuery=true
    )
    List<CollectionEntity> findAllByUser(@Param("user_id") int user_id);


    @Query(
            value="SELECT * FROM collections WHERE game_id =:game_id",
            nativeQuery=true
    )
    List<CollectionEntity> findAllByGame(@Param("game_id") int game_id);

}