package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="collections")
@NamedQuery(name="CollectionEntity.findAll", query="SELECT r FROM CollectionEntity r")
public class CollectionEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "game_id")
    private GameEntity game;

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
     * @param game of the collection
     * @param user of the collection
     */

    public CollectionEntity(GameEntity game, UserEntity user) {
        this.game = game;
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

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
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
                ", game=" + game +
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
