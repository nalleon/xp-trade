package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="favorites")
@NamedQuery(name="FavoriteEntity.findAll", query="SELECT r FROM FavoriteEntity r")
public class FavoriteEntity {

    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "game_id")
    private GameEntity game;

    /**
     * Default constructor of the class
     */
    public FavoriteEntity() {}

    /**
     * Constructor of the class
     * @param id of the favorite
     */
    public FavoriteEntity(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param user of the favorite
     * @param game of the favorite
     */
    public FavoriteEntity(UserEntity user, GameEntity game) {
        this.user = user;
        this.game = game;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FavoriteEntity{" +
                "id=" + id +
                ", user=" + user +
                ", game=" + game +
                '}';
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteEntity that = (FavoriteEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
