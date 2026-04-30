package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService service;
    @Test
    @DisplayName("Get channel")
    void getChannel(){
        Channel channel = service.getCompleteChannel("shortfilms", 25, 1, 5);
        System.out.println(channel);
    }

}