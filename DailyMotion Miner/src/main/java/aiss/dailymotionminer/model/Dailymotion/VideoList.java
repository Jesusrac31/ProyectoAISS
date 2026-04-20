package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video_List {

    @JsonProperty("list")
    private List<Video> list;

    @JsonProperty("list")
    public List<Video> getList() { return list; }

    @JsonProperty("list")
    public void setList(List<Video> list) { this.list = list; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Video_List.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("list");
        sb.append('=');
        sb.append(((this.list == null)?"<null>":this.list));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
