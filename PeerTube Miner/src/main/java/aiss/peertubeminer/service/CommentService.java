package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.peertube.Comment_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://peertube.tv/api/v1/";

    // Get the comments of a video
    public List<Comment> getVideoComments(String id, int maxComments) {
        // Initial uri
        String uri = BASE_URL + "videos/" + id + "/comment-threads";
        // List that will store all the comments read
        List<Comment> allComments = new ArrayList<>();

        // Check if there exist next pages and if we have not already read the max required
        while (uri != null && allComments.size()<maxComments) {
            ResponseEntity<Comment_Data> response = restTemplate.getForEntity(uri, Comment_Data.class);
            if (response.getBody() != null && response.getBody().getData() != null) {
                allComments.addAll(response.getBody().getData());
            }
            uri = getNextPageUrl(response.getHeaders());
        }

        // If we have read more comments than required because of page size, truncate the list
        if (allComments.size() > maxComments) {
            allComments = allComments.subList(0, maxComments);
        }
        return allComments;
    }

    // Auxiliary function to obtain next page of contents if no all in the same page
    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }
}
