package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
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
                ", name='" + name + '\'' +
                ", user_link='" + url + '\'' +
                ", picture_link='" + avatar_720_url + '\'' +
                '}';
    }

}
