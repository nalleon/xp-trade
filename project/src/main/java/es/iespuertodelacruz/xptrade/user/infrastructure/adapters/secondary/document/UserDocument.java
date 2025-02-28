package es.iespuertodelacruz.xptrade.user.infrastructure.adapters.secondary.document;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Objects;

/**
 * @author Nabil Leon Alvarez <@nalleon>
 */
@Document(collection = "usuarios")
public class UserDocument {

    @Id
    @Field(name = "_id")
    private String id;

    @Field(name = "nombre")
    private String name;

    @Field(name = "password")
    private String password;

    @Field(name = "correo")
    private String email;

    @Field(name = "rol")
    private String role;

    @Field(name = "verificado")
    private int verified;

    @Field(name = "token_verificacion")
    private String verificationToken;

    @Field(name = "fecha_creacion")
    private Date creationDate;

    @Field(name = "foto_perfil")
    private String profilePicture;

    public UserDocument() {}

    /**
     * Getters and setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "UserDocument{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", verified=" + verified +
                ", verificationToken='" + verificationToken + '\'' +
                ", creationDate=" + creationDate +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }

    /**
     * Tostring and equals
     */


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDocument that = (UserDocument) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
