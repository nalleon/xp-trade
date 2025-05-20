package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.shared.utils.DateToLongConverter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="comments")
@NamedQuery(name="CommentEntity.findAll", query="SELECT r FROM CommentEntity r")
public class CommentEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;

    @ManyToOne()
    @JoinColumn(nullable=true, name = "post_id")
    private PostEntity post;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "user_id")
    private UserEntity user;

    @Column(nullable=false, length=255, name = "content")
    private String content;

    @Column(nullable=false, length=45, name = "creation_date")
    @Convert(converter = DateToLongConverter.class)
    private Date creationDate;

    @ManyToOne()
    @JoinColumn(name = "replied_comment_id")
    private CommentEntity repliedComment;

    @OneToMany(mappedBy = "repliedComment")
    private List<CommentEntity> replies;


    /**
     * Default constructor of the class
     */
    public CommentEntity() {}


    /**
     * Constructor of the class
     * @param id of the comment
     */
    public CommentEntity(int id) {
        this.id = id;
    }


    /**
     * Constructor of the class
     * @param post of the comment
     * @param user of the comment
     * @param content of the comment
     */
    public CommentEntity(PostEntity post, UserEntity user, String content) {
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

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CommentEntity getRepliedComment() {
        return repliedComment;
    }

    public void setRepliedComment(CommentEntity repliedComment) {
        this.repliedComment = repliedComment;
    }

    public List<CommentEntity> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentEntity> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
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
        CommentEntity that = (CommentEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
