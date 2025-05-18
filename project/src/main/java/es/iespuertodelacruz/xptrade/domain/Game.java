package es.iespuertodelacruz.xptrade.domain;

import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Game {
    /**
     * Properties
     */
    private int id;

    private String title;

    private String coverArt;

    private String slug;
    private int rating;
    private String released;

    private Set<Tag> tagSet;
    private Set<Developer> developerSet;

    private Set<Genre> genreSet;

    private Set<Platform> platformSet;

    private Set<Publisher> publisherSet;

    /**
     * Default constructor of the class
     */
    public Game() {}


    /**
     * Constructor of the class
     * @param id of the game
     */
    public Game(int id) {
        this.id = id;
    }


    /**
     * Constructor of the class
     * @param title of the game
     */
    public Game(String title) {
        this.title = title;
    }

    /**
     * Constructor of the class
     * @param title of the game
     * @param coverArt of the game
     * @param slug of the game
     * @param rating of the game
     * @param released of the game
     * @param tagSet of the game
     * @param developerSet of the game
     * @param genreSet of the game
     * @param platformSet of the game
     * @param publisherSet of the game
     */
    public Game(String title, String coverArt, String slug, int rating, String released, Set<Tag> tagSet,
                Set<Developer> developerSet, Set<Genre> genreSet, Set<Platform> platformSet,
                Set<Publisher> publisherSet) {
        this.title = title;
        this.coverArt = coverArt;
        this.slug = slug;
        this.rating = rating;
        this.released = released;
        this.tagSet = tagSet;
        this.developerSet = developerSet;
        this.genreSet = genreSet;
        this.platformSet = platformSet;
        this.publisherSet = publisherSet;
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

    public Set<Developer> getDeveloperSet() {
        return developerSet;
    }

    public void setDeveloperSet(Set<Developer> developerSet) {
        this.developerSet = developerSet;
    }

    public Set<Genre> getGenreSet() {
        return genreSet;
    }

    public void setGenreSet(Set<Genre> genreSet) {
        this.genreSet = genreSet;
    }

    public Set<Platform> getPlatformSet() {
        return platformSet;
    }

    public void setPlatformSet(Set<Platform> platformSet) {
        this.platformSet = platformSet;
    }

    public Set<Publisher> getPublisherSet() {
        return publisherSet;
    }

    public void setPublisherSet(Set<Publisher> publisherSet) {
        this.publisherSet = publisherSet;
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

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", slug='" + slug + '\'' +
                ", rating=" + rating +
                ", released='" + released + '\'' +
                ", tagsSet=" + tagSet +
                ", developerSet=" + developerSet +
                ", genreSet=" + genreSet +
                ", platformSet=" + platformSet +
                ", publisherSet=" + publisherSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
