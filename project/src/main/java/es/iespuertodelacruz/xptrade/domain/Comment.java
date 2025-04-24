package es.iespuertodelacruz.xptrade.domain;


import es.iespuertodelacruz.xptrade.shared.utils.DateToLongConverter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class Comment {
    /**
     * Properties
     */
    private int id;
    
    private Post post;
    
    private User user;

    private String content;
    
    private Date creationDate;


    /**
     * Default constructor of the class
     */
    public Comment() {}

    /**
     * Constructor of the class
     * @param id of the comment
     */
    public Comment(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param post of the comment
     * @param user of the comment
     * @param content of the comment
     */
    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post=" + post +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comment that = (Comment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
