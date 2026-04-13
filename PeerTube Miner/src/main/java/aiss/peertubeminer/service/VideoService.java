package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.Video_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;

    private static final String BASE_URL = "https://peertube.tv/api/v1/";

    // Get the videos of a channel given the channelHandler
    public List<Video> getChannelVideos(String channelHandler, int maxVideos) {
        // Initial uri
        String uri = BASE_URL + "video-channels/" + channelHandler + "/videos";
        // List that will store all the videos read
        List<Video> allVideos = new ArrayList<>();

        // Check if there exist next pages and if we have not already read the max required
        while (uri != null && allVideos.size() < maxVideos) {
            ResponseEntity<Video_Data> response = restTemplate.getForEntity(uri, Video_Data.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                allVideos.addAll(response.getBody().getData());
            }

            uri = getNextPageUrl(response.getHeaders());
        }

        if (allVideos.size() > maxVideos) {
            allVideos = allVideos.subList(0,maxVideos);
        }
        return allVideos;
    }

    // Get a specific video given its id
    public Video getById(String id) {
        String uri = BASE_URL + "videos/" + id;
        return restTemplate.getForObject(uri, Video.class);
    }

    // Get complete information of a video (including its comments and captions)
    public Video getCompleteVideoInfo(String id, int maxComments) {
        // Get initial video info
        Video video = getById(id);

        // Get the comments of the video and add them to its 'comments' property
        List<Comment> videoComments = commentService.getVideoComments(id,maxComments);
        video.setComments(videoComments);

        // Get the captions of the video and add them to its 'captions' property
        List<Caption> videoCaptions = captionService.getVideoCaptions(id);
        video.setCaptions(videoCaptions);

        return video;
    }

    // Auxiliary function to obtain next page of contents if no all in the same page
    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }
}
