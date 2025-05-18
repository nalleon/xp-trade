package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="tags")
@NamedQuery(name="TagEntity.findAll", query="SELECT r FROM TagEntity r")
public class TagEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @Column(unique = true, nullable=false, length=45, name = "name")
    private String name;

    @ManyToMany(mappedBy = "tagEntitySet", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    Set<GameEntity> games;


    /**
     * Default constructor of the class
     */
    public TagEntity() {}

    /**
     * Constructor of the class
     * @param id of the genre
     */
    public TagEntity(int id) {
        this.id = id;
    }
    /**
     * Constructor of the class
     * @param name of the region
     */
    public TagEntity(String name) {
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
        return "TagEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity that = (TagEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
