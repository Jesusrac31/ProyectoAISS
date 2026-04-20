package aiss.peertubeminer.service.peertube;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.CommentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${peertubeminer.baseuri}")
    private static String BASE_URL;

    // Get the comments of a video
    public List<Comment> getVideoComments(String id, int maxComments) {
        // Determine how many comments to request per call
        // If maxComments >= 100 -> use 100 (API maximum limit)
        // If maxComments < 1 -> use 1 (minimum valid value)
        // Otherwise -> use maxComments
        int count = maxComments >= 100 ? 100 : Math.max(maxComments, 1);
        int start = 0;
        boolean hasMore = true;

        // List that will store all retrieved comments
        List<Comment> allComments = new ArrayList<>();

        // Continue requesting pages while there are remaining video comments
        // and we have not reached the desired number of comments
        while (hasMore && allComments.size()<maxComments) {
            // Build initial request URI
            String uri = BASE_URL + "videos/" + id + "/comment-threads?start=" + start + "&count=" + count;
            ResponseEntity<CommentData> response = restTemplate.getForEntity(uri, CommentData.class);

            if (response.getBody() != null && response.getBody().getData() != null) {
                allComments.addAll(response.getBody().getData());

                int totalComments = response.getBody().getTotal();
                if (allComments.size() >= totalComments) {
                    hasMore = false;
                } else {
                    start += count;
                }
            } else {
                hasMore = false;
            }
        }

        // If we have fetched more comments than requested, trim the list to required size
        if (allComments.size() > maxComments) {
            allComments = allComments.subList(0, maxComments);
        }
        return allComments;
    }
}
