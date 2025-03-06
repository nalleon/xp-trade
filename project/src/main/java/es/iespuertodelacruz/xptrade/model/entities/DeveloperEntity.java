package es.iespuertodelacruz.xptrade.model.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="developers")
@NamedQuery(name="DeveloperEntity.findAll", query="SELECT r FROM DeveloperEntity r")
public class DeveloperEntity {
        /**
         * Properties
         */
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(unique=true, nullable=false)
        private int id;

        @Column(unique = true, nullable=false, length=45, name = "name")
        private String name;

        @ManyToMany(mappedBy = "developerEntitySet", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
        Set<GameEntity> games;

        /**
         * Default constructor of the class
         */
        public DeveloperEntity() {}

        /**
         * Constructor of the class
         * @param name of the region
         */
        public DeveloperEntity(String name) {
            this.name = name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DeveloperEntity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperEntity that = (DeveloperEntity) o;
        return id == that.id;
    }

    @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
}
