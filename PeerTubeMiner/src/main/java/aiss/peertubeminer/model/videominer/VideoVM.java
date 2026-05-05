package aiss.peertubeminer.model.videominer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan C. Alonso
 */
// This is the Video class, it is used for obtaining information of videos that will be shared to video miner
// Once you obtain the information from the API, a service will transform those models into these
public class VideoVM {

    // The seven properties in the data model
    private String id;
    private String name;
    private String description;
    private String releaseTime;
    private UserVM user;
    private List<CommentVM> comments;
    private List<CaptionVM> captions;

    // Constructor
    public VideoVM(String id, String name, String description, String releaseTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.user = null;
        this.comments = new ArrayList<>();
        this.captions = new ArrayList<>();
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public UserVM getUser() {
        return user;
    }

    public void setUser(UserVM author) {
        this.user = author;
    }
    
    public List<CommentVM> getComments() {
        return comments;
    }

    public void setComments(List<CommentVM> comments) {
        this.comments = comments;
    }

    public List<CaptionVM> getCaptions() {
        return captions;
    }

    public void setCaptions(List<CaptionVM> captions) {
        this.captions = captions;
    }

    // Way of representing the class as a string
    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", author=" + user +
                ", comments=" + comments +
                ", captions=" + captions +
                '}';
    }
}
