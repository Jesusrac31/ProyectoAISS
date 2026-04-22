package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "captions")
public class Caption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @JsonProperty("id")
    private String id;

    @Column(name = "name")//
    @NotNull(message = "Channel name cannot be null")//
    @NotEmpty(message = "Caption name cannot be empty")//
    @JsonProperty("name")
    private String name;

    @Column(name = "language")//
    @NotNull(message = "Language cannot be null")//
    @NotEmpty(message = "Language cannot be empty")//
    @JsonProperty("language")
    private String language;

    // Empty constructor required by Spring
    public Caption(){

    }

    //Default constructor for Caption
    public Caption(String name, String language){
        this.name = name;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
