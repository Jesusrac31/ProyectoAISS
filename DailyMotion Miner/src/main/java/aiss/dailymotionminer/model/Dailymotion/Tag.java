package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * @author Juan C. Alonso
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

    @JsonIgnore
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonIgnore
    private String created_time;

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonIgnore
    public String getCreatedOn() {
        return created_time;
    }

    @JsonIgnore
    public void setCreatedOn(String created_time) {
        this.created_time = created_time;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + created_time + '\'' +
                '}';
    }
}
