package aiss.videominer.repository;

import aiss.videominer.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    /**
     * Finds comments using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'text': Partial match for the text.
     * 'createdOn': Exact match for the creation time.
     */
    @Query("SELECT c FROM Comment c WHERE " +
            "(:text IS NULL OR c.text LIKE CONCAT('%', :text, '%')) AND " +
            "(:createdOn IS NULL OR c.createdOn = :createdOn)")
    Page<Comment> findByFilters(@Param("text") String text,
                              @Param("createdOn") String createdOn,
                              Pageable paging);

    /**
     * Finds video comments using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'videoId': id of the video whose comments are retrieved
     * 'text': Partial match for the text.
     * 'createdOn': Exact match for the creation time.
     */
    @Query("SELECT c FROM Video v JOIN v.comments c WHERE " +
            "(v.id = :videoId) AND " +
            "(:text IS NULL OR c.text LIKE CONCAT('%', :text, '%')) AND " +
            "(:createdOn IS NULL OR c.createdOn = :createdOn)")
    Page<Comment> findByVideoIdAndFilters(@Param("videoId") String videoId,
                                          @Param("text") String text,
                                          @Param("createdOn") String createdOn,
                                          Pageable paging);
}
