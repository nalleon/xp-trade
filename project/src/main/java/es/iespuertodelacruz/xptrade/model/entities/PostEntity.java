package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="posts")
@NamedQuery(name="PostEntity.findAll", query="SELECT r FROM PostEntity r")
public class PostEntity {
}
