package aiss.peertubeminer.model.videominer;

import jakarta.persistence.*;

/**
 * @author Juan C. Alonso
 */
public class Comment {

    private String id;
    private String text;
    private String createdOn;

    public Comment(String id, String text, String createdOn) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
