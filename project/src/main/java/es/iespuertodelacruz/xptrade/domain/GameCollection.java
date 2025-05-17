package es.iespuertodelacruz.xptrade.domain;



import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class GameCollection {

    /**
     * Properties
     */
    private int id;
    private Game game;
    private Collection collection;
    private Region region;
    private Platform platform;

    /**
     * Default constructor
     */
    public GameCollection() {
    }

    /**
     * Constructor with ID for searches
     * @param id of GameCollection
     */
    public GameCollection(int id) {
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
    public GameCollection(int id, Game game, Collection collection, Region region, Platform platform) {
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
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
        GameCollection that = (GameCollection) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
