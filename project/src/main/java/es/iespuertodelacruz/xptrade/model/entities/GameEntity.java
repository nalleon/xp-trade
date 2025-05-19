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

    @Column(unique = true, nullable=false, length=100, name = "slug")
    private String slug;

    @Column(name = "rating")
    private int rating;

    @Column(length=100, name = "released")
    private String released;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_developers",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "developers_id")})
    private Set<DeveloperEntity> developerEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_tags",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id")})
    private Set<TagEntity> tagEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_genres",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id")})
    private Set<GenreEntity> genreEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_platforms",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "platform_id")})
    private Set<PlatformEntity> platformEntitySet;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "games_publishers",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "publisher_id")})
    private Set<PublisherEntity> publisherEntitySet;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<FavoriteEntity> favoriteEntitySet;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<PostEntity> postEntitySet;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<GameCollectionEntity> gameCollectionSet;

    /**
     * Default constructor of the class
     */
    public GameEntity() {}

    /**
     * Constructor of the class
     * @param id of the genre
     */
    public GameEntity(int id) {
        this.id = id;
    }

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
     * @param slug of the game
     * @param developerEntitySet of the game
     * @param genreEntitySet of the game
     * @param platformEntitySet of the game
     * @param publisherEntitySet of the game
     */
    public GameEntity(String title, String coverArt, String slug, Set<DeveloperEntity>
            developerEntitySet, Set<GenreEntity> genreEntitySet, Set<PlatformEntity> platformEntitySet,
                      Set<PublisherEntity> publisherEntitySet) {
        this.title = title;
        this.coverArt = coverArt;
        this.slug = slug;
        this.developerEntitySet = developerEntitySet;
        this.genreEntitySet = genreEntitySet;
        this.platformEntitySet = platformEntitySet;
        this.publisherEntitySet = publisherEntitySet;
    }

    /**
     * Constructor of the class
     * @param id of the game
     * @param title of the game
     * @param coverArt of the game
     * @param slug of the game
     * @param rating of the game
     * @param released of the game
     * @param developerEntitySet of the game
     * @param tagEntitySet of the game
     * @param genreEntitySet of the game
     * @param platformEntitySet of the game
     * @param publisherEntitySet of the game
     */
    public GameEntity(int id, String title, String coverArt, String slug, int rating, String released,
                      Set<DeveloperEntity> developerEntitySet, Set<TagEntity> tagEntitySet, Set<GenreEntity> genreEntitySet,
                      Set<PlatformEntity> platformEntitySet, Set<PublisherEntity> publisherEntitySet) {
        this.id = id;
        this.title = title;
        this.coverArt = coverArt;
        this.slug = slug;
        this.rating = rating;
        this.released = released;
        this.developerEntitySet = developerEntitySet;
        this.tagEntitySet = tagEntitySet;
        this.genreEntitySet = genreEntitySet;
        this.platformEntitySet = platformEntitySet;
        this.publisherEntitySet = publisherEntitySet;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Set<TagEntity> getTagEntitySet() {
        return tagEntitySet;
    }

    public void setTagEntitySet(Set<TagEntity> tagEntitySet) {
        this.tagEntitySet = tagEntitySet;
    }


    @Override
    public String toString() {
        return "GameEntity{" +
                "publisherEntitySet=" + publisherEntitySet +
                ", platformEntitySet=" + platformEntitySet +
                ", genreEntitySet=" + genreEntitySet +
                ", tagEntitySet=" + tagEntitySet +
                ", developerEntitySet=" + developerEntitySet +
                ", released='" + released + '\'' +
                ", rating=" + rating +
                ", slug='" + slug + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
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
