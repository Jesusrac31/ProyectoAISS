
package aiss.peertubeminer.model.peertube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("avatars")
    private List<Avatar> avatars;

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

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("avatars")
    public List<Avatar> getAvatars() {
        return avatars;
    }

    @JsonProperty("avatars")
    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        return "Account {\n" +
                "    id=" + id + ",\n" +
                "    name='" + name + "',\n" +
                "    url='" + url + "',\n" +
                "    avatars=" + avatars + "\n" +
                "}";
    }
}
