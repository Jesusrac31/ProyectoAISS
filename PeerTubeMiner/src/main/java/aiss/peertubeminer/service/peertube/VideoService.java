package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.VideoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${peertubeminer.baseuri}")
    private String BASE_URL;

    // Get the videos of a channel given the channelHandler
    public List<Video> getChannelVideos(String channelHandler, int maxVideos) {
        // Determine how many videos to request per call
        // If maxVideosComments >= 100 -> use 100 (API maximum limit)
        // If maxVideos < 1 -> use 1 (minimum valid value)
        // Otherwise -> use maxVideos
        int count = maxVideos >= 100 ? 100 : Math.max(maxVideos, 1);
        int start = 0;
        boolean hasMore = true;

        // List that will store all retrieved videos
        List<Video> allVideos = new ArrayList<>();

        // Continue requesting pages while there are remaining channel videos
        // and we have not reached the desired number of videos
        while (hasMore && allVideos.size() < maxVideos) {
            // Build initial request URI
            String uri = BASE_URL + "video-channels/" + channelHandler + "/videos?start=" + start + "&count=" + count;
            ResponseEntity<VideoData> response = restTemplate.getForEntity(uri, VideoData.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                allVideos.addAll(response.getBody().getData());

                int totalVideos = response.getBody().getTotal();
                if (allVideos.size() >= totalVideos) {
                    hasMore = false;
                } else {
                    start += count;
                }
            } else {
                hasMore = false;
            }
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
}
