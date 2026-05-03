package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Video;
import aiss.dailymotionminer.model.Dailymotion.VideoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    SubtitleService subtitleService;

    private static final String BASE_URL = "https://api.dailymotion.com/";

    // Method to GET the videos from a Channel
    public List<Video> getChannelVideos(String channelHandler, int maxVideos, int maxTags, int maxPages) {

        // If maxVideos > 100 (API Limit) -> limit = 100
        // If maxVideos <= 100 -> limit = maxVideos
        long count =maxVideos > 100 ? 100 : Math.max(1, maxVideos);
        List<Video> allVideos = new ArrayList<>();
        // Build uri
        // Keep in mind there's more than one page, so we will iterate until no more pages, limit of pages reached or limit of videos reached
        int pageCount = 1; // Start at the first page
        while(allVideos.size()<maxVideos && pageCount<=maxPages) { // As long as max page or max videos not reached stay in loop
            String uri = BASE_URL + "/videos?channel=" + channelHandler + "&limit=" +count+ "&page="+pageCount+
                    "&fields=id,title,description,created_time,tags,owner.id,owner.screenname,owner.url,owner.avatar_720_url"; // Los campos que necesitamos de cada video
            VideoList videoList = restTemplate.getForObject(uri, VideoList.class);
            if(videoList==null || videoList.getList() == null || videoList.getList().isEmpty()) {
                return allVideos; // If something went wrong or channel has no videos return empty list
            } else {
                allVideos.addAll(videoList.getList()); // Otherwise, add all videos from that page to the list
            }
            pageCount++; // Increment page number
        }

        // Obten los subtitulos de todos los videos
        allVideos.forEach(video -> video.setSubtitles(subtitleService.getVideoSubtitles(video.getId())));

        // Limita las tags de todos los videos
        allVideos.forEach(video -> {
            List<String> tags = video.getTags();
            if (tags != null && tags.size() > maxTags) video.setTags(tags.subList(0, maxTags));
        });

        return allVideos.stream().limit(maxVideos).toList();
    }

    // Method to GET a video with an ID
    public Video getById(String id) {
        String uri = BASE_URL + "video/" + id +
                "?fields=id,title,description,created_time,tags,owner.id,owner.screenname,owner.url,owner.avatar_720_url"; // Los campos que necesitamos de cada video
        return restTemplate.getForObject(uri, Video.class);
    }
}