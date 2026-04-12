package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.videominer.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CommentRepository {
    List<Comment> comments = new ArrayList<>();

    public CommentRepository() {
        // Mock data for testing
        comments.add(
                new Comment(
                        UUID.randomUUID().toString(),
                        "Great video",
                        "2026-01-10T10:00:00Z"
                )
        );
        comments.add(
                new Comment(
                        UUID.randomUUID().toString(),
                        "Very useful",
                        "2026-01-11T10:00:00Z"
                )
        );
        comments.add(
                new Comment(
                        UUID.randomUUID().toString(),
                        "Thanks for sharing",
                        "2026-01-12T10:00:00Z"
                )
        );
    }

    // Find all operation, you will be able to obtain all comments stored
    // For now, this operation returns the info in list comments
    public List<Comment> findAll() {
        return comments;
    }

    // Find one comment with some specific id
    public Comment findOneById(String id) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some comment which is passed as a parameter
    public Comment create(Comment comment) {
        Comment newComment = new Comment(
                UUID.randomUUID().toString(),
                comment.getText(),
                comment.getCreatedOn()
        );
        comments.add(newComment);
        return newComment; // Returns the created comment
    }

    // Updates some comment with some id
    public void update(Comment updatedComment, String id) {
        Comment existingComment = findOneById(id);
        int i = comments.indexOf(existingComment);
        updatedComment.setId(existingComment.getId());
        comments.set(i, updatedComment);
    }

    // Remove some comment with some id
    public void delete(String id) {
        comments.removeIf(comment -> comment.getId().equals(id));
    }
}
