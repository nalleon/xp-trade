package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.model.entities.PostEntity;
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
public interface IPostEntityRepository extends JpaRepository<PostEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM comments AS r WHERE r.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);


    @Query(
            value="SELECT * FROM comments WHERE user_id =:user_id",
            nativeQuery=true
    )
    List<PostEntity> findAllByUser(@Param("user_id") int user_id);


    @Query(
            value="SELECT * FROM comments WHERE post_id =:postId",
            nativeQuery=true
    )
    List<PostEntity> findAllByGame(@Param("post_id") int post_id);
}