package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.shared.utils.DateToLongConverter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="posts")
@NamedQuery(name="PostEntity.findAll", query="SELECT r FROM PostEntity r")
public class PostEntity {
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

    @Column(nullable=false, length=255, name = "content")
    private String content;

    @Column(nullable=true, length=255, name = "picture")
    private String picture;


    @Column(nullable=false, length=45, name = "creation_date")
    @Convert(converter = DateToLongConverter.class)
    private Date creationDate;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<CommentEntity> commentEntitySet;


    /**
     * Default constructor of the class
     */
    public PostEntity() {}

    /**
     * Constructor of the class
     * @param game of the game
     * @param user of the user
     * @param content of the post
     * @param picture of the post
     */
    public PostEntity(GameEntity game, UserEntity user, String content, String picture) {
        this.game = game;
        this.user = user;
        this.content = content;
        this.picture = picture;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", gameId=" + game +
                ", userId=" + user +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
