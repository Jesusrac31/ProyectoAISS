package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.dailymotion.com/";

    // Method to GET the tags (comments) of a video
    public List<String> getVideoTags(String id, int maxTags) {
        // Since tags is an array, no need to calculate the limit. It will simply be maxTags
        List<String> tags;
        String uri = BASE_URL + "video/" + id + "?fields=tags"; // Build uri
        Video video = restTemplate.getForObject(uri, Video.class); // Get video

        if (video==null || video.getTags()==null || video.getTags().isEmpty()) {
            tags = new ArrayList<>(); // If something went wrong, tags will be empty
        } else {
            tags = video.getTags().stream().limit(maxTags).toList(); // Tags are cut to the limit set in the parameter
        }
        return tags;
    }
}