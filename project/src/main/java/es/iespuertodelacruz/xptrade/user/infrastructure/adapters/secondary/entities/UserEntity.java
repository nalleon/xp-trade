package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.secondary.entities;

import es.iespuertodelacruz.xptrade.game.infrastructure.adapters.secondary.entities.GameEntity;
import es.iespuertodelacruz.xptrade.shared.utils.DateToLongConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */

@Entity
@Table(name="usuarios")
@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int Id;


    @Column(unique = true, nullable=false, length=45, name = "nombre")
    private String name;

    @Column(nullable=false, length=200)
    private String password;

    @Column(unique = true, nullable=false, length=100, name = "correo")
    private String email;

    @Column(nullable=false, length=45, name = "rol")
    private String role;

    @Column(name = "verificado")
    private int verified;

    @Column(length=255, name = "token_verificacion")
    private String verificationToken;

    @Column(nullable=false, length=45, name = "fecha_creacion")
    @Convert(converter = DateToLongConverter.class)
    private Date creationDate;

    @Column(nullable=true, length=255, name = "foto_perfil")
    private String profilePicture;

    @OneToMany(mappedBy="player1")
    private List<GameEntity> games1;

    @OneToMany(mappedBy="player2")
    private List<GameEntity> games2;

    /**
     * Getters and setters
     */
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", verified=" + verified +
                ", verificationToken='" + verificationToken + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity entity = (UserEntity) o;
        return Id == entity.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
