package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.model.entities.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    Set<Developer> developerSet;

    Set<Genre> genreSet;

    Set<Platform> platformSet;

    Set<Publisher> publisherSet;

    Set<Region> regionSet;

    /**
     * Default constructor of the class
     */
    public Game() {}

    /**
     * Constructor of the class
     * @param coverArt of the game
     */
    public Game(String coverArt) {
        this.coverArt = coverArt;
    }

    /**
     * Constructor of the class
     * @param title of the game
     * @param coverArt of the game
     * @param developerSet of the game
     * @param genreSet of the game
     * @param platformSet of the game
     * @param publisherSet of the game
     * @param regionSet of the game
     */
    public Game(String title, String coverArt, Set<Developer> developerSet, Set<Genre> genreSet,
                Set<Platform> platformSet, Set<Publisher> publisherSet,
                Set<Region> regionSet) {
        this.title = title;
        this.coverArt = coverArt;
        this.developerSet = developerSet;
        this.genreSet = genreSet;
        this.platformSet = platformSet;
        this.publisherSet = publisherSet;
        this.regionSet = regionSet;
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

    public Set<Region> getRegionSet() {
        return regionSet;
    }

    public void setRegionSet(Set<Region> regionSet) {
        this.regionSet = regionSet;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverArt='" + coverArt + '\'' +
                ", developerSet=" + developerSet +
                ", genreSet=" + genreSet +
                ", platformSet=" + platformSet +
                ", publisherSet=" + publisherSet +
                ", regionSet=" + regionSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
