package aiss.dailymotionminer.model.videominer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan C. Alonso
 */
// This is the Channel class, it is used for obtaining information of channels that will be shared to video miner
// Once you obtain the information from the API, a service will transform those models into these
public class ChannelVM {

    // The five properties in the data model
    private String id;
    private String name;
    private String description;
    private String createdTime;
    private List<VideoVM> videos;

    // Constructor
    public ChannelVM(String id, String name, String description, String createdTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.videos = new ArrayList<>();
    }

    // Setters and getters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<VideoVM> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoVM> videos) {
        this.videos = videos;
    }

    // Way of representing the class as a string
    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", videos=" + videos +
                '}';
    }
}
