package aiss.videominer.repository;

import aiss.videominer.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    /**
     * Finds videos using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'name': Exact match for the video name.
     * 'description': Partial match for the description.
     * 'releaseTime': Exact match for the release time.
     * 'username': Exact match for the username.
     */
    @Query("SELECT v FROM Video v LEFT JOIN v.user u WHERE " +
            "(:name IS NULL OR v.name = :name) AND " +
            "(:description IS NULL OR v.description LIKE CONCAT('%', :description, '%')) AND " +
            "(:releaseTime IS NULL OR v.releaseTime = :releaseTime) AND " +
            "(:username IS NULL OR u.name = :username)")
    Page<Video> findByFilters(@Param("name") String name,
                                @Param("description") String description,
                                @Param("releaseTime") String releaseTime,
                                @Param("username") String username,
                                Pageable paging);

    /**
     * Finds videos of a channel using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'channelId': id of the channel whose videos are retrieved
     * 'name': Exact match for the video name.
     * 'description': Partial match for the description.
     * 'releaseTime': Exact match for the release time.
     * 'username': Exact match for the username.
     */
    @Query("SELECT v FROM Channel c JOIN c.videos v LEFT JOIN v.user u WHERE " +
            "(c.id = :channelId) AND " +
            "(:name IS NULL OR v.name = :name) AND " +
            "(:description IS NULL OR v.description LIKE CONCAT('%', :description, '%')) AND " +
            "(:releaseTime IS NULL OR v.releaseTime = :releaseTime) AND " +
            "(:username IS NULL OR u.name = :username)")
    Page<Video> findByChannelIdAndFilters(@Param("channelId") String channelId,
                                          @Param("name") String name,
                                          @Param("description") String description,
                                          @Param("releaseTime") String releaseTime,
                                          @Param("username") String username,
                                          Pageable paging);
}
