package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://peertube.tv/api/v1/";
    @Autowired
    VideoService videoService;
    // Retrieves complete information of a channel,
    // including its videos and each videos' comments and captions
    public Channel getCompleteChannelInfo(String channelHandler, int maxVideos, int maxComments) {
        // Get basic channel information (without videos)
        Channel channel = getById(channelHandler);
        // List to store channel's videos with complete information
        List<Video> allVideos = new ArrayList<>();

        // Iterate through channel's videos
        for (Video video : videoService.getChannelVideos(channelHandler, maxVideos)) {
            // For each video, retrieve its complete information
            // including comments and captions
            allVideos.add(videoService.getCompleteVideoInfo(video, maxComments));
        }

        // Set the 'videos' property of the channel object
        channel.setVideos(allVideos);
        return channel;
    }

    // Retrieves a channel by its identifier (basic info only)
    public Channel getById(String channelHandler) {
        String uri = BASE_URL + "video-channels/" + channelHandler;
        return restTemplate.getForObject(uri, Channel.class);
    }
}
