package es.iespuertodelacruz.xptrade.model.documents;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Document(collection = "feedback")
public class FeedbackDocument {

    @Id
    @Field(name = "_id")
    private String id;

    @Field(name = "content")
    private String content;

    /**
     * Default constructor
     */
    public FeedbackDocument() {
    }

    /**
     * Constructor with all content
     * @param content of feedback
     */
    public FeedbackDocument(String id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Getters and setters
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
