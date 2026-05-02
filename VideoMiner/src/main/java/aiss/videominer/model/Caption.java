package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "captions")
@JsonPropertyOrder({ "id", "link", "language" })
public class Caption {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID) While creating the channel, the IDs are passed by the User, not generated automatically
    @JsonProperty("id")
    private String id;

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
    public Caption(String name, String language){
        this.link = name;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return link;
    }

    public void setName(String name) {
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
