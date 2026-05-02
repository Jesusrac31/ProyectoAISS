package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "videos")
@JsonPropertyOrder({ "id", "name", "description", "releaseTime", "user", "captions", "comments" })
public class Video {

    @PrePersist // Annotation which allows to execute code when the entity is saved for the first time. In this case we create a random ID for the Video object if the ID was not specified by the User.
    public void generateIdIfNotSpecified(){
        if (this.id.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)  The IDs of the video objects are provided by the user
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @Column(name = "name")//
    @NotEmpty(message = "Video name cannot be empty")
    private String name;

    @JsonProperty("description")
    @Column(columnDefinition="TEXT", name = "description")//
    //@NotEmpty(message = "Video description cannot be empty")//
    private String description;

    @JsonProperty("releaseTime")
    @Column(name = "releaseTime")//
    @NotEmpty(message = "Video release time cannot be empty")
    private String releaseTime;

    @JsonProperty("user")
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @JsonProperty("comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    private List<Comment> comments = new ArrayList<>();

    @JsonProperty("captions")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    private List<Caption> captions = new ArrayList<>();

    // Empty constructor required by Spring
    public Video(){
    }

    //Default constructor for Video
    public Video(String name, String description, String releaseTime, User user){
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

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
