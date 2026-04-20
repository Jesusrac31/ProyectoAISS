package aiss.dailymotionminer.repository;

import aiss.dailymotionminer.model.videominer.CommentVM;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CommentRepository {
    List<CommentVM> comments = new ArrayList<>();

    public CommentRepository() {
        // Mock data for testing
        comments.add(
                new CommentVM(
                        "ForTests",
                        "Great video",
                        "2026-01-10T10:00:00Z"
                )
        );
        comments.add(
                new CommentVM(
                        UUID.randomUUID().toString(),
                        "Great video",
                        "2026-01-10T10:00:00Z"
                )
        );
        comments.add(
                new CommentVM(
                        UUID.randomUUID().toString(),
                        "Very useful",
                        "2026-01-11T10:00:00Z"
                )
        );
        comments.add(
                new CommentVM(
                        UUID.randomUUID().toString(),
                        "Thanks for sharing",
                        "2026-01-12T10:00:00Z"
                )
        );
    }

    // Find all operation, you will be able to obtain all comments stored
    // For now, this operation returns the info in list comments
    public List<CommentVM> findAll() {
        return comments;
    }

    // Find one comment with some specific id
    public CommentVM findOneById(String id) {
        return comments.stream()
                .filter(comment -> comment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some comment which is passed as a parameter
    public CommentVM create(CommentVM comment) {
        CommentVM newComment = new CommentVM(
                UUID.randomUUID().toString(),
                comment.getText(),
                comment.getCreatedOn()
        );
        comments.add(newComment);
        return newComment; // Returns the created comment
    }

    // Updates some comment with some id
    public void update(CommentVM updatedComment, String id) {
        CommentVM existingComment = findOneById(id);
        int i = comments.indexOf(existingComment);
        updatedComment.setId(existingComment.getId());
        comments.set(i, updatedComment);
    }

    // Remove some comment with some id
    public void delete(String id) {
        comments.removeIf(comment -> comment.getId().equals(id));
    }
}
