package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.CollectionEntity;
import es.iespuertodelacruz.xptrade.model.entities.GameCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Repository
public interface IGameCollectionEntityRepository extends JpaRepository<GameCollectionEntity, Integer> {
    @Modifying
    @Query(
            value="DELETE FROM games_collections AS r WHERE r.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);

    @Query(
            value="SELECT * FROM games_collections WHERE collection_id =:collection_id",
            nativeQuery=true
    )
    List<GameCollectionEntity> findAllByCollection(@Param("collection_id") int collectionId);
}
