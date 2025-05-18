package es.iespuertodelacruz.xptrade.domain;

import java.util.Objects;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Tag {
    /**
     * Properties
     */
    private int id;
    private String name;


    /**
     * Default constructor of the class
     */
    public Tag() {
    }

    /**
     * Constructor of the class
     * @param id of the role
     */
    public Tag(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the role
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Full constructor of the class
     * @param id of the role
     * @param name of the role
     */
    public Tag(int id, String name) {
        this.id = id;
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
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
