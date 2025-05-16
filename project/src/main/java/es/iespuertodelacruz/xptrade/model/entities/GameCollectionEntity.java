package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Entity
@Table(name = "games_collections")
@NamedQuery(name="GameCollectionEntity.findAll", query="SELECT r FROM GameCollectionEntity r")
public class GameCollectionEntity {
    /**
     * Properties
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "game_id")
    private GameEntity game;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "collection_id")
    private CollectionEntity collection;

    @ManyToOne()
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne()
    @JoinColumn(name = "platform_id")
    private PlatformEntity platform;

    /**
     * Default constructor
     */
    public GameCollectionEntity() {
    }

    /**
     * Constructor with ID for searches
     * @param id of GameCollection
     */
    public GameCollectionEntity(int id) {
        this.id = id;
    }

    /**
     * Constructor with all parameters
     * @param id of GameCollection
     * @param game of GameCollection
     * @param collection of GameCollection
     * @param region of GameCollection
     * @param platform of GameCollection
     */
    public GameCollectionEntity(int id, GameEntity game, CollectionEntity collection, RegionEntity region, PlatformEntity platform) {
        this.id = id;
        this.game = game;
        this.collection = collection;
        this.region = region;
        this.platform = platform;
    }

    /**
     * Getters and setters
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public CollectionEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionEntity collection) {
        this.collection = collection;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public PlatformEntity getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEntity platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "GameCollectionEntity{" +
                "id=" + id +
                ", game=" + game +
                ", collection=" + collection +
                ", region=" + region +
                ", platformEntity=" + platform +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameCollectionEntity that = (GameCollectionEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
