package aiss.dailymotionminer.repository;

import aiss.dailymotionminer.model.videominer.VideoVM;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VideoRepository {
    List<VideoVM> videos = new ArrayList<>();

    public VideoRepository() {
        // Mock data for testing
        videos.add(
                new VideoVM(
                        "ForTests",
                        "ForTesting",
                        "Basics of Spring Boot",
                        "2026-01-10T10:00:00Z"
                )
        );
        videos.add(
                new VideoVM(
                        UUID.randomUUID().toString(),
                        "Spring Boot Intro",
                        "Basics of Spring Boot",
                        "2026-01-10T10:00:00Z"
                )
        );
        videos.add(
                new VideoVM(
                        UUID.randomUUID().toString(),
                        "REST API Guide",
                        "How to build REST APIs",
                        "2026-01-11T10:00:00Z"
                )
        );
        videos.add(
                new VideoVM(
                        UUID.randomUUID().toString(),
                        "JPA Mapping",
                        "Entity relationships",
                        "2026-01-12T10:00:00Z"
                )
        );
    }

    // Find all operation, you will be able to obtain all videos stored
    // For now, this operation returns the info in list videos
    public List<VideoVM> findAll() {
        return videos;
    }

    // Find one video with some specific id
    public VideoVM findOneById(String id) {
        return videos.stream()
                .filter(video -> video.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some video which is passed as a parameter
    public VideoVM create(VideoVM video) {
        VideoVM newVideo = new VideoVM(
                UUID.randomUUID().toString(),
                video.getName(),
                video.getDescription(),
                video.getReleaseTime()
        );
        newVideo.setAuthor(video.getAuthor());
        newVideo.setComments(video.getComments());
        newVideo.setCaptions(video.getCaptions());
        videos.add(newVideo);
        return newVideo; // Returns the created video
    }

    // Updates some video with some id
    public void update(VideoVM updatedVideo, String id) {
        VideoVM existingVideo = findOneById(id);
        int i = videos.indexOf(existingVideo);
        updatedVideo.setId(existingVideo.getId());
        videos.set(i, updatedVideo);
    }

    // Remove some video with some id
    public void delete(String id) {
        videos.removeIf(video -> video.getId().equals(id));
    }
}
