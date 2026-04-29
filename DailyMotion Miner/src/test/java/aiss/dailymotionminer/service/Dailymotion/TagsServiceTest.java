package aiss.dailymotionminer.service.Dailymotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TagsServiceTest {
    @Autowired
    TagsService service;
    @Test
    @DisplayName("Get video tags")
    void getTags(){
        List<String> tags = service.getVideoTags("xa74i0s", 5);
        System.out.println(tags);
    }

}