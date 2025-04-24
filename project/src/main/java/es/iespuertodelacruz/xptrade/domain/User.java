package es.iespuertodelacruz.xptrade.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class User {
    /**
     * Properties
     */
    private int id;
    private String username;

    private String password;

    private String email;

    private Role role;

    private int verified;

    private String verificationToken;

    private Date creationDate;

    private String profilePicture;

    /**
     * Default constructor of the class
     */
    public User() {
    }

    /**
     * Constructor of the class
     * @param id of the user
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * Constructor of the class
     * @param username of the user
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Constructor of the class
     * @param username of the user
     * @param password of the user
     * @param email of the user
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, int verified) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.verified = verified;
    }


    public User(String username, String email, String password, String profilePicture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
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

    public String getVerificationToken() {
        return verificationToken;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", verified=" + verified +
                ", verificationToken='" + verificationToken + '\'' +
                ", creationDate=" + creationDate +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
