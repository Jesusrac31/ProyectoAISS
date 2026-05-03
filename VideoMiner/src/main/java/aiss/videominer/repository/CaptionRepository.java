package aiss.videominer.repository;

import aiss.videominer.model.Caption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptionRepository extends JpaRepository<Caption, String> {
    /**
     * Finds captions using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'link': Exact match for the link.
     * 'language': Exact match for the language.
     */
    @Query("SELECT c FROM Caption c WHERE " +
            "(:link IS NULL OR c.link = :link) AND " +
            "(:language IS NULL OR c.language = :language)")
    Page<Caption> findByFilters(@Param("link") String text,
                                @Param("language") String createdOn,
                                Pageable paging);

    /**
     * Finds video captions using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'videoId': id of the video whose comments are retrieved
     * 'link': Exact match for the link.
     * 'language': Exact match for the language.
     */
    @Query("SELECT c FROM Video v JOIN v.captions c WHERE " +
            "(v.id = :videoId) AND " +
            "(:link IS NULL OR c.link = :link) AND " +
            "(:language IS NULL OR c.language = :language)")
    Page<Caption> findByVideoIdAndFilters(@Param("videoId") String videoId,
                                          @Param("link") String link,
                                          @Param("language") String language,
                                          Pageable paging);
}
