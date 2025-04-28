package es.iespuertodelacruz.xptrade.domain;


import java.util.Objects;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

public class Feedback {
    /**
     * Properties
     */
    private String id;
    private String content;

    /**
     * Default constructor
     */
    public Feedback() {
    }

    /**
     * Constructor with ID for searches
     * @param id of feedback
     */
    public Feedback(String id) {
        this.id = id;
    }

    /**
     * Constructor with all parameters
     * @param id of feedback
     * @param content of feedback
     */
    public Feedback(String id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Getters and setters
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
