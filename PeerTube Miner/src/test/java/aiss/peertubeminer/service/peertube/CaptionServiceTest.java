package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Caption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CaptionServiceTest {
    @Autowired
    CaptionService service;

    @Test
    @DisplayName("Get video captions")
    void getCaptions() {
        List<Caption> captions = service.getVideoCaptions("samGb77rmc7VZKtiMcfXnM");
        System.out.println(captions);
    }
}