package es.iespuertodelacruz.xptrade.domain;

import java.util.Objects;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Publisher {
    /**
     * Properties
     */
    private int id;

    private String name;


    /**
     * Default constructor of the class
     */
    public Publisher() {}
    /**
     * Constructor of the class
     * @param id of the publisher
     */
    public Publisher(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the region
     */
    public Publisher(String name) {
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
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
