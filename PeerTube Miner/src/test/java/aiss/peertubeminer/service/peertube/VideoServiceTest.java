package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Video;
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
    @DisplayName("Get all channel videos")
    void getChannelVideos() {
        List<Video> videos = service.getChannelVideos("bloodaxegamingnor@peertube.linuxrocks.online", 1);
        System.out.println("Number of videos: " + videos.size());
        System.out.println(videos);
    }

    @Test
    @DisplayName("Get video by id")
    void getVideo() {
        Video video = service.getById("wsa7yQiUSVnw9HTwba1KiN");
        System.out.println(video);
    }

    @Test
    @DisplayName("Get video with complete information")
    void getCompleteVideoInfo() {
        Video video = service.getById("110571");
        Video videoWithInfo = service.getCompleteVideoInfo(video, 10);
        System.out.println(videoWithInfo);
    }
}