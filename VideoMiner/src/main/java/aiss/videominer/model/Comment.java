package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    @Column(columnDefinition="TEXT", name = "text")//
    @NotNull(message = "Text of the comment cannot be null")//
    @NotEmpty(message = "Text of the message cannot be empty") // OR actually can?
    private String text;

    @JsonProperty("createdOn")
    @Column(name = "createdOn")//
    @NotNull(message = "The creation data of the comment cannot be null")//
    @Past//
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
