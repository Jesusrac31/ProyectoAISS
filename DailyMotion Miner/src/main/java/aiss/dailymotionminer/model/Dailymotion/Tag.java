package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {

    @JsonIgnore
    private String id;

    @JsonProperty("tags")
    private List<String> tags;

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
    public List<String> getText() {
        return tags;
    }

    @JsonProperty("text")
    public void setText(List<String> tags) {
        this.tags = tags;
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
                ", text='" + tags + '\'' +
                ", createdOn='" + created_time + '\'' +
                '}';
    }
}
