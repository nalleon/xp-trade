package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

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
}
