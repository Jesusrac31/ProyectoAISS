package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "captions")
@JsonPropertyOrder({ "id", "link", "language" })
public class Caption {

    @Id
    @JsonProperty("id")
    // If id is given in JSON body use the provided value, else generate a random UUID
    private String id = UUID.randomUUID().toString();

    @Column(name = "link")//
    @NotEmpty(message = "Caption link cannot be empty")//
    @JsonProperty("link")
    private String link;

    @Column(name = "language")//
    @NotEmpty(message = "Caption language cannot be empty")//
    @JsonProperty("language")
    private String language;

    // Empty constructor required by Spring
    public Caption(){
    }

    //Default constructor for Caption
    public Caption(String link, String language){
        this.link = link;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String name) {
        this.link = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", name='" + link + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
