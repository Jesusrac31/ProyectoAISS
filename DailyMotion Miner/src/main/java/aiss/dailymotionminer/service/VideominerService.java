package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.ChannelVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideominerService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${videominer.uri}")
    private static String BASE_URL;

    public ChannelVM postChannel(ChannelVM channel){
        String uri = BASE_URL;
        return restTemplate.postForObject(uri, channel, ChannelVM.class);
    }

}
