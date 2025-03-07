package es.iespuertodelacruz.xptrade.domain;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Favorite {

    /**
     * Properties
     */

    private int id;
    
    private User user;

    private Game game;

    /**
     * Default constructor of the class
     */
    public Favorite() {}

    /**
     * Constructor of the class
     * @param user of the favorite
     * @param game of the favorite
     */
    public Favorite(User user, Game game) {
        this.user = user;
        this.game = game;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userId=" + user +
                '}';
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Favorite that = (Favorite) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
