package aiss.dailymotionminer.service;

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

    // Get the tags (comments) of a video
    public List<String> getVideoTags(String id, int maxTags) {
        // If maxTags > 150 (maximum size for tags) -> limit = 150 tags
        // If maxTags <= 150 -> limit = maxTags
        long count = maxTags > 150 ? 150 : Math.max(1, maxTags);
        List<String> tags;
        String uri = BASE_URL + id + "?fields=tags";
        Video video = restTemplate.getForObject(uri, Video.class);
        if (video==null || video.getTags()==null || video.getTags().isEmpty()) {
            tags = new ArrayList<>();
        } else {
            tags = video.getTags().stream().limit(count).toList();
        }
        return tags;
    }
}