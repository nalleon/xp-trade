package es.iespuertodelacruz.xptrade.user.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    private int Id;
    private String name;

    private String password;

    private String email;

    private String role;

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
     * @param name of the user
     * @param password of the user
     * @param email of the user
     */
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password, String email, int verified) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.verified = verified;
    }


    public User(String name, String email, String password, String profilePicture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Id == user.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
