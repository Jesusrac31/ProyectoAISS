package aiss.dailymotionminer.model.Dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    @JsonProperty("id")
    private String id;

    @JsonProperty("screenname")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("avatar_720_url")
    private String avatar_720_url;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("screenname")
    public String getScreenname() {
        return name;
    }

    @JsonProperty("screenname")
    public void setScreenname(String name) {
        this.name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("avatar_720_url")
    public String getAvatar_link() {
        return avatar_720_url;
    }

    @JsonProperty("avatar_720_url")
    public void setAvatar_link(String avatar_720_url) { this.avatar_720_url = avatar_720_url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", screenname='" + name + '\'' +
                ", user_link='" + url + '\'' +
                ", picture_link='" + avatar_720_url + '\'' +
                '}';
    }

}
