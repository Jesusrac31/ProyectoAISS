package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService service;

    @Test
    @DisplayName("Get video comments")
    void getComments() {
        List<Comment> comments = service.getVideoComments("wsa7yQiUSVnw9HTwba1KiN", 1);
        System.out.println(comments);
    }
}