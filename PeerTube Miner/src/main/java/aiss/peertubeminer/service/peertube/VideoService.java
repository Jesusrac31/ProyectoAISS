package aiss.peertubeminer.service.peertube;

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
        // Determine how many videos to request per call
        // If maxVideosComments >= 100 -> use 100 (API maximum limit)
        // If maxVideos < 1 -> use 1 (minimum valid value)
        // Otherwise -> use maxVideos
        int count = maxVideos >= 100 ? 100 : Math.max(maxVideos, 1);

        // Build initial request URI
        String uri = BASE_URL + "video-channels/" + channelHandler + "/videos?count=" + count;
        // List that will store all retrieved videos
        List<Video> allVideos = new ArrayList<>();

        // Continue requesting pages when there is a next page (uri != null)
        // and we have not reached the desired number of videos
        while (uri != null && allVideos.size() < maxVideos) {
            ResponseEntity<Video_Data> response = restTemplate.getForEntity(uri, Video_Data.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                allVideos.addAll(response.getBody().getData());
            }

            uri = getNextPageUrl(response.getHeaders());
        }

        // If we have fetched more videos than requested, trim the list to required size
        if (allVideos.size() > maxVideos) {
            allVideos = allVideos.subList(0,maxVideos);
        }
        return allVideos;
    }

    // Retrieves a video by its identifier (basic info only)
    public Video getById(String id) {
        String uri = BASE_URL + "videos/" + id;
        return restTemplate.getForObject(uri, Video.class);
    }

    // Retrieves a video including its comments and captions
    public Video getCompleteVideoInfo(Video video, int maxComments) {
        // Extract video id from video object
        String id = video.getId();

        // Retrieve video's comments and add them to its 'comments' property
        List<Comment> videoComments = commentService.getVideoComments(id,maxComments);
        video.setComments(videoComments);

        // Retrieve video's captions and add them to its 'captions' property
        List<Caption> videoCaptions = captionService.getVideoCaptions(id);
        video.setCaptions(videoCaptions);

        return video;
    }

    // Auxiliary function to obtain URL of next page of contents
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
