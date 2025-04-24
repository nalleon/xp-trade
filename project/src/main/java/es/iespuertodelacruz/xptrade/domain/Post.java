package es.iespuertodelacruz.xptrade.domain;

import es.iespuertodelacruz.xptrade.model.entities.CommentEntity;
import es.iespuertodelacruz.xptrade.shared.utils.DateToLongConverter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class Post {
    /**
     * Properties
     */
    private int id;

    private Game game;

    private User user;

    private String content;

    private String picture;

    private Date creationDate;

    /**
     * Default constructor of the class
     */
    public Post() {}


    /**
     * Constructor of the class
     * @param id of the post
     */
    public Post(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param game of the game
     * @param user of the user
     * @param content of the post
     * @param picture of the post
     */
    public Post(Game game, User user, String content, String picture) {
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
        return "Post{" +
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
        Post that = (Post) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
