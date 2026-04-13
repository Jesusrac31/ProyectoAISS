package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Caption_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CaptionService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://peertube.tv/api/v1/";

    // Get the captions of a video
    public List<Caption> getVideoCaptions(String id) {
        String uri = BASE_URL + "videos/" + id + "/captions";
        Caption_Data data = restTemplate.getForObject(uri, Caption_Data.class);
        assert data != null;
        return data.getData();
    }
}