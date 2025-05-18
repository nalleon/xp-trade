package es.iespuertodelacruz.xptrade.model.repository;

import es.iespuertodelacruz.xptrade.domain.Game;
import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import es.iespuertodelacruz.xptrade.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Repository
public interface IGameEntityRepository  extends JpaRepository<GameEntity, Integer> {

    @Modifying
    @Query(
            value="DELETE FROM games AS u WHERE u.id=:id",
            nativeQuery=true
    )
    int deleteEntityById(@Param("id") Integer id);

    @Query(
            value="SELECT * FROM games WHERE title=:title",
            nativeQuery=true
    )
    Optional<GameEntity> findByTitle(@Param("title") String title);

    @Query(
            value = "SELECT g.* FROM games g " +
                    "INNER JOIN games_platforms gp ON g.id = gp.game_id " +
                    "INNER JOIN platforms p ON gp.platform_id = p.id " +
                    "WHERE p.id = :platform_id",
            nativeQuery = true
    )
    List<GameEntity> findAllByPlatform(@Param("platform_id") int platformId);

    @Query(
            value = "SELECT g.* FROM games g " +
                    "INNER JOIN games_genres gp ON g.id = gp.game_id " +
                    "INNER JOIN genres p ON gp.genre_id = p.id " +
                    "WHERE p.id = :genre_id",
            nativeQuery = true
    )
    List<GameEntity> findAllByGenre(@Param("genre_id") int genreId);

    @Query(
            value = "SELECT g.* FROM games g " +
                    "INNER JOIN games_publishers gp ON g.id = gp.game_id " +
                    "INNER JOIN publishers p ON gp.publisher_id = p.id " +
                    "WHERE p.id = :publisher_id",
            nativeQuery = true
    )
    List<GameEntity> findAllByPublisher(@Param("publisher_id") int publisherId);

    @Query(
            value = "SELECT g.* FROM games g " +
                    "INNER JOIN games_regions gp ON g.id = gp.game_id " +
                    "INNER JOIN regions p ON gp.region_id = p.id " +
                    "WHERE p.id = :region_id",
            nativeQuery = true
    )
    List<GameEntity> findAllByRegion(@Param("region_id") int regionId);

    @Query(
            value = "SELECT g.* FROM games g " +
                    "INNER JOIN games_developers gp ON g.id = gp.game_id " +
                    "INNER JOIN developers p ON gp.developer_id = p.id " +
                    "WHERE p.id = :developer_id",
            nativeQuery = true
    )
    List<GameEntity> findAllByDeveloper(@Param("developer_id") int developerId);

    @Modifying
    @Query(
            value = "DELETE FROM games_platforms AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteGamePlatformRelation(@Param("gameId") int gameId);

    @Modifying
    @Query(
            value = "DELETE FROM games_regions AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteGameRegionRelation(@Param("gameId") int gameId);

    @Modifying
    @Query(
            value = "DELETE FROM games_publishers AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteGamePublisherRelation(@Param("gameId") int gameId);

    @Modifying
    @Query(
            value = "DELETE FROM games_developers AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteGameDeveloperRelation(@Param("gameId") int gameId);

    @Modifying
    @Query(
            value = "DELETE FROM games_genres AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteGameGenreRelation(@Param("gameId") int gameId);

    @Modifying
    @Query(
            value = "DELETE FROM games_tags AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteGameTagRelation(@Param("gameId") int gameId);


    @Modifying
    @Query(
            value = "DELETE FROM posts AS gp " +
                    "WHERE gp.game_id=:gameId",
            nativeQuery = true
    )
    int deleteFromPosts(@Param("gameId") int gameId);


    @Modifying
    @Query(
            value = "INSERT INTO games_platforms (game_id, platform_id) VALUES (:gameId, :platformId)",
            nativeQuery = true
    )
    void insertGamePlatformRelation(@Param("gameId") int gameId, @Param("platformId") int platformId);

    @Modifying
    @Query(
            value = "INSERT INTO games_regions (game_id, region_id) VALUES (:gameId, :regionId)",
            nativeQuery = true
    )
    void insertGameRegionRelation(@Param("gameId") int gameId, @Param("regionId") int regionId);
    @Modifying
    @Query(
            value = "INSERT INTO games_regions (game_id, publisher_id) VALUES (:gameId, :publisherId)",
            nativeQuery = true
    )
    void insertGamePublisherRelation(@Param("gameId") int gameId, @Param("publisherId") int publisherId);

    @Modifying
    @Query(
            value = "INSERT INTO games_developers (game_id, developer_id) VALUES (:gameId, :developerId)",
            nativeQuery = true
    )
    void insertGameDeveloperRelation(@Param("gameId") int gameId, @Param("developerId") int developerId);

    @Modifying
    @Query(
            value = "INSERT INTO games_genres (game_id, genre_id) VALUES (:gameId, :genreId)",
            nativeQuery = true
    )
    void insertGameGenreRelation(@Param("gameId") int gameId, @Param("genreId") int genreId);


}
