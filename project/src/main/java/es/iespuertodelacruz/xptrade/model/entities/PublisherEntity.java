package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Entity
@Table(name="publishers")
@NamedQuery(name="PublisherEntity.findAll", query="SELECT r FROM PublisherEntity r")
public class PublisherEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @Column(unique = true, nullable=false, length=45, name = "name")
    private String name;

    @ManyToMany(mappedBy = "publisherEntitySet", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    Set<GameEntity> games;

    /**
     * Default constructor of the class
     */
    public PublisherEntity() {}

    /**
     * Constructor of the class
     * @param id of the publisher
     */
    public PublisherEntity(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the publisher
     */
    public PublisherEntity(String name) {
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
        return "PublisherEntity{" +
                "id=" + id +
                ", username='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PublisherEntity that = (PublisherEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
