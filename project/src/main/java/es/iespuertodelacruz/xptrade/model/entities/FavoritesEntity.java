package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

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

//    @Column(unique = true, nullable=false, length=45, name = "name")
    private int userId;

//    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private int gameId;
    /**
     * Default constructor of the class
     */
    public FavoritesEntity() {}

    /**
     * Constructor of the class
     * @param userId of the user
     * @param gameId of the game
     */
    public FavoritesEntity(int userId, int gameId) {
        this.userId = userId;
        this.gameId = gameId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "FavoritesEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", gameId=" + gameId +
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
