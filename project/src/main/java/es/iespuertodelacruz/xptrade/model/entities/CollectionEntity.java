package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name ="collections")
@NamedQuery(name="CollectionEntity.findAll", query="SELECT r FROM CollectionEntity r")
public class CollectionEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @OneToMany()
    @JoinColumn(nullable=false, name = "game_id")
    private GameCollectionEntity gameCollection;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "user_id")
    private UserEntity user;

    /**
     * Default constructor of the class
     */
    public CollectionEntity() {
    }

    /**
     * Constructor of the class
     * @param id of the collection
     */
    public CollectionEntity(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param gameCollection of the collection
     * @param user of the collection
     */

    public CollectionEntity(GameCollectionEntity gameCollection, UserEntity user) {
        this.gameCollection = gameCollection;
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

    public GameCollectionEntity getGameCollection() {
        return gameCollection;
    }

    public void setGameCollection(GameCollectionEntity gameCollection) {
        this.gameCollection = gameCollection;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CollectionEntity{" +
                "id=" + id +
                ", game=" + gameCollection +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CollectionEntity that = (CollectionEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
