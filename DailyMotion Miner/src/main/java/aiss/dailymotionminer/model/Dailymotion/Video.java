package aiss.dailymotionminer.model.Dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("owner")
    private String ownerId;

    @JsonProperty("created_time")
    private String created_time;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonIgnore
    private List<Subtitle> subtitles;

    @JsonIgnore
    private Owner owner;


    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String description) { this.description = description; }

    @JsonProperty("owner")
    public String getOwnerId() { return ownerId; }
    @JsonProperty("owner")
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    @JsonProperty("created_time")
    public String getCreated_time() { return created_time; }

    @JsonProperty("created_time")
    public void setCreated_time(String created_time) { this.created_time = created_time; }

    @JsonProperty("tags")
    public List<String> getTags() { return tags; }

    @JsonProperty("tags")
    public void setTags(List<String> tags) { this.tags = tags; }

    @JsonIgnore
    public List<Subtitle> getSubtitles() { return subtitles; }

    @JsonIgnore
    public void setSubtitles(List<Subtitle> subtitles) { this.subtitles = subtitles; }

    @JsonIgnore
    public Owner getOwner(){
        return owner;
    }
    @JsonIgnore
    public void setOwner(Owner owner){
        this.owner=owner;
    }

    @Override
    public String toString() {
        return "Video {\n" +
                "    id=" + id + ",\n" +
                "    title='" + title + "',\n" +
                "    description='" + description + "',\n" +
                "    created_time='" + created_time + "',\n" +
                "    owner=" + ownerId + ",\n" +
                "    tags=" + tags + ",\n" +
                "    subtitles=" + subtitles + "\n" +
                "}";
    }
}
