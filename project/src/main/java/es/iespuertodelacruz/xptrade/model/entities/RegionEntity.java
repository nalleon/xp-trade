package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="regions")
@NamedQuery(name="RegionEntity.findAll", query="SELECT r FROM RegionEntity r")
public class RegionEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @Column(unique = true, nullable=false, length=45, name = "name")
    private String name;

    @OneToMany(mappedBy = "region")
    private Set<GameCollectionEntity> gameCollectionSet;

    /**
     * Default constructor of the class
     */
    public RegionEntity() {}

    /**
     * Constructor of the class
     * @param id of the region
     */
    public RegionEntity(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param name of the region
     */
    public RegionEntity(String name) {
        this.name = name;
    }

    /**
     * Full constructor of the class
     * @param id of the region
     * @param name of the region
     */
    public RegionEntity(int id, String name) {
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
        return "RegionEntity{" +
                "id=" + id +
                ", username='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegionEntity that = (RegionEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
