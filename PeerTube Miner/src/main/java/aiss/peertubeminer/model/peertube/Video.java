
package aiss.peertubeminer.model.peertube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("account")
    private Account account;

    // Create variables to store captions and comments of a video (Do not come in JSON response)
    @JsonIgnore
    private List<Caption> captions;
    @JsonIgnore
    private List<Comment> comments;

    // Getters and Setters
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

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("account")
    public Account getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(Account account) {
        this.account = account;
    }

    // Getter for created variable 'captions'
    @JsonIgnore
    public List<Caption> getCaptions() {
        return captions;
    }

    // Setter for created variable 'captions'
    @JsonIgnore
    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

    // Getter for created variable 'comments'
    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }

    // Setter for created variable 'videos'
    @JsonIgnore
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Video {\n" +
                "    id=" + id + ",\n" +
                "    name='" + name + "',\n" +
                "    description='" + description + "',\n" +
                "    publishedAt='" + publishedAt + "',\n" +
                "    account=" + account + ",\n" +
                "    captions=" + captions + ",\n" +
                "    comments=" + comments + "\n" +
                "}";
    }

}
