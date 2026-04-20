package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    @NotEmpty(message = "Channel name cannot be empty")
    private String name;
    @JsonProperty("description")
    private String description;

    // Not included in response. If not found in the JSON, assigned as null
    private String createdAt;

    // Not included in response
    @JsonIgnore
    private List<Video> videos;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public String getCreatedAt() { return createdAt; }

    @JsonIgnore
    public void setCreatedAt( String createdAt) { this.createdAt = createdAt; }

    // Getter for new variable
    @JsonIgnore
    public List<Video> getVideos() {
        return videos;
    }

    // Setter for new variable
    @JsonIgnore
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", videos=" + videos +
                ", createdAt=" + createdAt + '\'' +
                '}';
    }
}
