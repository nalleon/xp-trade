package es.iespuertodelacruz.xptrade.domain;

import java.util.Objects;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Developer {
    /**
     * Properties
     */
    private int id;

    private String name;


    /**
     * Default constructor of the class
     */
    public Developer() {}

    /**
     * Constructor of the class
     * @param id of the developer
     */
    public Developer(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the developer
     */
    public Developer(String name) {
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
        return "Developer{" +
                "id=" + id +
                ", username='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id == developer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
