package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.videominer.Video;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VideoRepository {
    List<Video> videos = new ArrayList<>();

    public VideoRepository() {
        // Mock data for testing
        videos.add(
                new Video(
                        "ForTests",
                        "ForTesting",
                        "Basics of Spring Boot",
                        "2026-01-10T10:00:00Z"
                )
        );
        videos.add(
                new Video(
                        UUID.randomUUID().toString(),
                        "Spring Boot Intro",
                        "Basics of Spring Boot",
                        "2026-01-10T10:00:00Z"
                )
        );
        videos.add(
                new Video(
                        UUID.randomUUID().toString(),
                        "REST API Guide",
                        "How to build REST APIs",
                        "2026-01-11T10:00:00Z"
                )
        );
        videos.add(
                new Video(
                        UUID.randomUUID().toString(),
                        "JPA Mapping",
                        "Entity relationships",
                        "2026-01-12T10:00:00Z"
                )
        );
    }

    // Find all operation, you will be able to obtain all videos stored
    // For now, this operation returns the info in list videos
    public List<Video> findAll() {
        return videos;
    }

    // Find one video with some specific id
    public Video findOneById(String id) {
        return videos.stream()
                .filter(video -> video.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some video which is passed as a parameter
    public Video create(Video video) {
        Video newVideo = new Video(
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
    public void update(Video updatedVideo, String id) {
        Video existingVideo = findOneById(id);
        int i = videos.indexOf(existingVideo);
        updatedVideo.setId(existingVideo.getId());
        videos.set(i, updatedVideo);
    }

    // Remove some video with some id
    public void delete(String id) {
        videos.removeIf(video -> video.getId().equals(id));
    }
}
