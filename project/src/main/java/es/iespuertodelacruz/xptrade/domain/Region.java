package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.model.entities.GameEntity;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class Region {
    /**
     * Properties
     */
    private int id;

    private String name;


    /**
     * Default constructor of the class
     */
    public Region() {}

    /**
     * Constructor of the class
     * @param id of the region
     */
    public Region(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the region
     */
    public Region(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", username='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return id == region.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
