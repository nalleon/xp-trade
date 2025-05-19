package es.iespuertodelacruz.xptrade.domain;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Collection {
    /**
     * Properties
     */
    private int id;
    
    private User user;

    /**
     * Default constructor of the class
     */
    public Collection() {
    }

    /**
     * Constructor of the class
     * @param id of the collection
     */
    public Collection(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param user of the collection
     */

    public Collection(User user) {
        this.user = user;
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
        return "Collection{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
