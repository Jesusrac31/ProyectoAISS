
package aiss.peertubeminer.model.peertube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("createdAt")
    private String createdAt;

    // Create variable videos to store channel videos (Does not come in JSON response)
    @JsonIgnore
    private List<Video> videos;

    //Getters and Setters
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Getter for created variable 'videos'
    @JsonIgnore
    public List<Video> getVideos() {
        return videos;
    }

    // Setter for created variable 'videos'
    @JsonIgnore
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Channel {\n" +
                "id=" + id + ",\n" +
                "name=" + name + ",\n" +
                "description=" + description + ",\n" +
                "createdAt=" + createdAt + ",\n" +
                "videos=" + videos + "\n" +
                "}";
    }

}
