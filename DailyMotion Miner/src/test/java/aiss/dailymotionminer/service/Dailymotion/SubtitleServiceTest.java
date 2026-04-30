package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Subtitle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SubtitleServiceTest {
    @Autowired
    SubtitleService service;
    @Test
    @DisplayName("Get video subtitles")
    void getSubtitles(){
        List<Subtitle> subtitles = service.getVideoSubtitles("xa7kbou");
        System.out.println(subtitles);
    }
}