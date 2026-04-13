package aiss.peertubeminer.service;

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
    @DisplayName("Get all channel videos") // Devuelve avatar en el owner como una lista, Cambiar para coger primer enlace
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
    @DisplayName("Get video with complete info")
    void getCompleteVideoInfo() {
        Video video = service.getCompleteVideoInfo("110571", 10);
        System.out.println(video);
    }
}