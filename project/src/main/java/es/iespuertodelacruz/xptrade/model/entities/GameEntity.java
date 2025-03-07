package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="games")
@NamedQuery(name="GameEntity.findAll", query="SELECT r FROM GameEntity r")
public class GameEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @Column(unique = true, nullable=false, length=100, name = "title")
    private String title;

    @Column(nullable=false, length=255, name = "cover_art")
    private String coverArt;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_developers",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "developers_id")})
    Set<DeveloperEntity> developerEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_genres",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id")})
    Set<GenreEntity> genreEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_platforms",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "platform_id")})
    Set<PlatformEntity> platformEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_publishers",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "publisher_id")})
    Set<PublisherEntity> publisherEntitySet;


    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_regions",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "region_id")})
    Set<RegionEntity> regionEntitySet;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<FavoriteEntity> favoriteEntitySet;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<PostEntity> postEntitySet;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<CollectionEntity> collectionEntitySet;

    /**
     * Default constructor of the class
     */
    public GameEntity() {}

    /**
     * Constructor of the class
     * @param title of the game
     * @param coverArt of the game
     */
    public GameEntity(String title, String coverArt) {
        this.title = title;
        this.coverArt = coverArt;
    }

    /**
     * Constructor of the class
     * @param title of the game
     * @param coverArt of the game
     * @param developerEntitySet of the game
     * @param genreEntitySet of the game
     * @param platformEntitySet of the game
     * @param publisherEntitySet of the game
     * @param regionEntitySet of the game
     */

    public GameEntity(String title, String coverArt, Set<DeveloperEntity> developerEntitySet,
                      Set<GenreEntity> genreEntitySet, Set<PlatformEntity> platformEntitySet,
                      Set<PublisherEntity> publisherEntitySet, Set<RegionEntity> regionEntitySet) {
        this.title = title;
        this.coverArt = coverArt;
        this.developerEntitySet = developerEntitySet;
        this.genreEntitySet = genreEntitySet;
        this.platformEntitySet = platformEntitySet;
        this.publisherEntitySet = publisherEntitySet;
        this.regionEntitySet = regionEntitySet;
    }

    /**
     * Getters and setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }

    public Set<DeveloperEntity> getDeveloperEntitySet() {
        return developerEntitySet;
    }

    public void setDeveloperEntitySet(Set<DeveloperEntity> developerEntitySet) {
        this.developerEntitySet = developerEntitySet;
    }

    public Set<GenreEntity> getGenreEntitySet() {
        return genreEntitySet;
    }

    public void setGenreEntitySet(Set<GenreEntity> genreEntitySet) {
        this.genreEntitySet = genreEntitySet;
    }

    public Set<PlatformEntity> getPlatformEntitySet() {
        return platformEntitySet;
    }

    public void setPlatformEntitySet(Set<PlatformEntity> platformEntitySet) {
        this.platformEntitySet = platformEntitySet;
    }

    public Set<PublisherEntity> getPublisherEntitySet() {
        return publisherEntitySet;
    }

    public void setPublisherEntitySet(Set<PublisherEntity> publisherEntitySet) {
        this.publisherEntitySet = publisherEntitySet;
    }

    public Set<RegionEntity> getRegionEntitySet() {
        return regionEntitySet;
    }

    public void setRegionEntitySet(Set<RegionEntity> regionEntitySet) {
        this.regionEntitySet = regionEntitySet;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", developerEntitySet=" + developerEntitySet +
                ", genreEntitySet=" + genreEntitySet +
                ", platformEntitySet=" + platformEntitySet +
                ", publisherEntitySet=" + publisherEntitySet +
                ", regionEntitySet=" + regionEntitySet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
