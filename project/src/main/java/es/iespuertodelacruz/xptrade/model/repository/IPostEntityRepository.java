package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.PostEntity;
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
public interface IPostEntityRepository extends JpaRepository<PostEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM posts AS r WHERE r.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);


    @Query(
            value=
                    "SELECT * FROM posts " +
                    "ORDER BY creation_date DESC",
            nativeQuery=true
    )
    List<PostEntity> findAllLatest();

    @Query(
            value = "SELECT * FROM posts AS p " +
                    "INNER JOIN users AS u ON u.id = p.user_id " +
                    "WHERE u.username = :username " +
                    "ORDER BY p.creation_date DESC " +
                    "LIMIT 1",
            nativeQuery = true
    )
    Optional<PostEntity> findLatest(@Param("username") String username);

    @Query(
            value="SELECT * FROM posts WHERE user_id =:user_id",
            nativeQuery=true
    )
    List<PostEntity> findAllByUser(@Param("user_id") int user_id);


    @Query(
            value="SELECT * FROM posts WHERE game_id =:postId",
            nativeQuery=true
    )
    List<PostEntity> findAllByGame(@Param("game_id") int game_id);
}