package aiss.dailymotionminer.service.Dailymotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import aiss.dailymotionminer.model.Dailymotion.SubtitleList;
import aiss.dailymotionminer.model.Dailymotion.Subtitle;

import java.util.List;

@Service
public class SubtitleService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.dailymotion.com/";

    // Get subtitles (captions) of a video
    public List<Subtitle> getVideoSubtitles(String id) {
        String uri = BASE_URL + "video/" + id + "/subtitles" +
                "?fields=id,url,language"; // Fields needed in a subtitle
        SubtitleList list = restTemplate.getForObject(uri, SubtitleList.class);
        assert list != null;
        return list.getList();
    }
}