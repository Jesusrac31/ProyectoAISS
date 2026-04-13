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
        // Determine how many comments to request per call
        // If maxComments >= 100 -> use 100 (API maximum limit)
        // If maxComments < 1 -> use 1 (minimum valid value)
        // Otherwise -> use maxComments
        int count = maxComments >= 100 ? 100 : Math.max(maxComments, 1);

        // Build initial request URI
        String uri = BASE_URL + "videos/" + id + "/comment-threads?count=" + count;
        // List that will store all retrieved comments
        List<Comment> allComments = new ArrayList<>();

        // Continue requesting pages when there is a next page (uri != null)
        // and we have not reached the desired number of comments
        while (uri != null && allComments.size()<maxComments) {
            ResponseEntity<Comment_Data> response = restTemplate.getForEntity(uri, Comment_Data.class);
            if (response.getBody() != null && response.getBody().getData() != null) {
                allComments.addAll(response.getBody().getData());
            }
            uri = getNextPageUrl(response.getHeaders());
        }

        // If we have fetched more comments than requested, trim the list to required size
        if (allComments.size() > maxComments) {
            allComments = allComments.subList(0, maxComments);
        }
        return allComments;
    }

    // Auxiliary function to obtain URL of next page of contents
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
