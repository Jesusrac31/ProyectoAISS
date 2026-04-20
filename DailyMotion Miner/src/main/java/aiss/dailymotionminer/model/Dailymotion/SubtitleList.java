package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubtitleList {

    @JsonProperty("data")
    private List<Subtitle> list;

    @JsonProperty("data")
    public List<Subtitle> getData() { return list; }

    @JsonProperty("data")
    public void setData(List<Subtitle> data) { this.list = data; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(VideoList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
