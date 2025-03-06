package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="collections")
@NamedQuery(name="CollectionEntity.findAll", query="SELECT r FROM CollectionEntity r")
public class CollectionEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;
}
