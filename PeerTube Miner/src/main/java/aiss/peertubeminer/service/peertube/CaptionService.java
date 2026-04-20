package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.CaptionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CaptionService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${peertubeminer.baseuri}")
    private static String BASE_URL;

    // Get the captions of a video
    public List<Caption> getVideoCaptions(String id) {
        String uri = BASE_URL + "videos/" + id + "/captions";
        CaptionData data = restTemplate.getForObject(uri, CaptionData.class);
        assert data != null;
        return data.getData();
    }
}