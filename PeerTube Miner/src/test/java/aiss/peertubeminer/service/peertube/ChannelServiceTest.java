package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService service;

    @Test
    @DisplayName("Get a channel by channelHandler")
    void findChannel() {
        Channel channel = service.getById("bloodaxegamingnor@peertube.linuxrocks.online");
        assertNotNull(channel);
        System.out.println(channel);
    }

    @Test
    @DisplayName("Get channel with complete information")
    void getCompleteChannelInfo() {
        Channel channel = service.getCompleteChannelInfo("bloodaxegamingnor@peertube.linuxrocks.online",2,10 );
        assertNotNull(channel);
        System.out.println("Total number of videos retrieved: " + channel.getVideos().size());
        System.out.println(channel);
    }
}