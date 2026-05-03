package aiss.videominer.repository;

import aiss.videominer.model.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, String> {
    /**
     * Finds channels using dynamic optional filters.
     * If a parameter is NULL, its condition is ignored.
     * 'name': Exact match for the channel name.
     * 'description': Partial match for the description.
     * 'createdTime': Exact match for the creation time.
     */
    @Query("SELECT c FROM Channel c WHERE " +
            "(:name IS NULL OR c.name = :name) AND " +
            "(:description IS NULL OR c.description LIKE CONCAT('%', :description, '%')) AND " +
            "(:createdTime IS NULL OR c.createdTime = :createdTime)")
    Page<Channel> findByFilters(@Param("name") String name,
                                @Param("description") String description,
                                @Param("createdTime") String createdTime,
                                Pageable paging);

}
