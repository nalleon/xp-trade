package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="favorites")
@NamedQuery(name="FavoritesEntity.findAll", query="SELECT r FROM FavoritesEntity r")
public class FavoritesEntity {

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
    public FavoritesEntity() {}

    /**
     * Constructor of the class
     * @param user of the user
//     * @param game of the game
     */
    public FavoritesEntity(UserEntity user) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FavoritesEntity{" +
                "id=" + id +
                ", userId=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FavoritesEntity that = (FavoritesEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
