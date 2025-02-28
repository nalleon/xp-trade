package es.iespuertodelacruz.xptrade.model.entities;

import es.iespuertodelacruz.xptrade.shared.utils.DateToLongConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;


/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Entity
@Table(name="users")
@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u")
public class UserEntity {
    /**
     * Properties
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private int Id;

    @Column(unique = true, nullable=false, length=45, name = "username")
    private String username;

    @Column(nullable=false, length=200, name = "password")
    private String password;

    @Column(unique = true, nullable=false, length=100, name = "email")
    private String email;

    @ManyToOne()
    @JoinColumn(nullable=false, name = "role_id")
    private int roleId;

    @Column(name = "verified")
    private int verified;

    @Column(length=255, name = "verification_token")
    private String verificationToken;

    @Column(nullable=false, length=45, name = "creation_token")
    @Convert(converter = DateToLongConverter.class)
    private Date creationDate;

    @Column(nullable=true, length=255, name = "profile_picture")
    private String profilePicture;

    /**
     * Default constructor of the class
     */
    public UserEntity() {}

    /**
     * Getters and setters
     */
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + roleId + '\'' +
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
