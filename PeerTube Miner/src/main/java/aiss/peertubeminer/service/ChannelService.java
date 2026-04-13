package aiss.peertubeminer.service;

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
    // Get the complete information of a channel (including its videos and the comments and
    // captions of the videos)
    public Channel getCompleteChannelInfo(String channelHandler, int maxVideos, int maxComments) {
        // Get general channel info (without videos)
        Channel channel = getOneChannel(channelHandler);
        // List for storing the channel videos with complete info
        List<Video> allVideos = new ArrayList<>();

        for (Video v : videoService.getChannelVideos(channelHandler, maxVideos)) {
            allVideos.add(videoService.getCompleteVideoInfo(v.getId().toString(), maxComments));
        }

        // Set the 'videos' property
        channel.setVideos(allVideos);
        return channel;
    }

    public Channel getOneChannel(String channelHandler) {
        String uri = BASE_URL + "video-channels/" + channelHandler;
        return restTemplate.getForObject(uri, Channel.class);
    }
}
