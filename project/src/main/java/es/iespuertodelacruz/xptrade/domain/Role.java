package es.iespuertodelacruz.xptrade.domain;

import java.util.Objects;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
public class Role {
    /**
     * Properties
     */
    private int id;
    private String name;


    /**
     * Default constructor of the class
     */
    public Role() {
    }

    /**
     * Constructor of the class
     * @param id of the role
     */
    public Role(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the role
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Full constructor of the class
     * @param id of the role
     * @param name of the role
     */
    public Role(int id, String name) {
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
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
