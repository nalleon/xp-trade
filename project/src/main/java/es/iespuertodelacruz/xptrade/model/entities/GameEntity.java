package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="games")
@NamedQuery(name="GameEntity.findAll", query="SELECT r FROM GameEntity r")
public class GameEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int id;
}
