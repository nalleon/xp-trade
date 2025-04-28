package es.iespuertodelacruz.xptrade.model.documents;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */

@Document(collection = "feedback")
public class FeedbackDocument {

    @Field(name = "content")
    private String content;

    /**
     * Default constructor
     */
    public FeedbackDocument() {
    }

    /**
     * Constructor with feedback content
     * @param content of feedback
     */
    public FeedbackDocument(String content) {
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
}
