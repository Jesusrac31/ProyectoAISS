package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "comments")
@JsonPropertyOrder({ "id", "text", "createdOn" })
public class Comment {

    @PrePersist // Annotation which allows to execute code when the entity is saved for the first time. In this case we create a random ID for the Comment object if the ID was not specified by the User.
    public void generateIdIfNotSpecified(){
        if (this.id == null || this.id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID) While creating the channel, the IDs are passed by the User, not generated automatically
    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    @Column(columnDefinition="TEXT", name = "text")
    @NotEmpty(message = "Text of the message cannot be empty") // OR actually can?
    private String text;

    @JsonProperty("createdOn")
    @Column(name = "createdOn")
    @NotNull(message = "Comment creation date cannot be null")
    private String createdOn;

    // Empty constructor required by Spring
    public Comment(){

    }

    public Comment(String text, String createdOn){
        this.text = text;
        this.createdOn = createdOn;
    }

    public String getId() { return id; }

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
