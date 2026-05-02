package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VideoServiceTest {
    @Autowired
    VideoService service;
    @Test
    @DisplayName("Get channel videos")
    void getVideos(){
        List<Video> videos = service.getChannelVideos("tv", 25, 1, 2);
        System.out.println(videos);
    }
}