package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="comments")
@NamedQuery(name="CommentEntity.findAll", query="SELECT r FROM CommentEntity r")
public class CommentEntity {
}
