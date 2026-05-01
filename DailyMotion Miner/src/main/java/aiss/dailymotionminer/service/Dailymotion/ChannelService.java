package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.dailymotion.com/";
    @Autowired
    VideoService videoService;
    // Retrieves complete information of a channel given its identifier and a maximum number of videos and tags
    public Channel getCompleteChannel(String channelHandler, int maxVideos, int maxTags,int maxPages) {
        // Retrieve channel with basic info
        Channel channel = getById(channelHandler);
        // Set videos of the channel object
        channel.setVideos(videoService.getChannelVideos(channelHandler, maxVideos, maxTags, maxPages));
        return channel;
    }
    // Retrieves a channel by its identifier
    public Channel getById(String channelHandler) {
        String uri = BASE_URL + "/channel/" + channelHandler; // Build uri
        return restTemplate.getForObject(uri, Channel.class); // Get object
    }
}
