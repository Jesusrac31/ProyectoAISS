package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.videominer.Video;
import aiss.peertubeminer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos") // The path where you can access to this part of the API
public class VideoController {
    private final VideoRepository videoRepository; // Storage of the info obtained

    // Constructor of the controller, from one repository, it creates the controller in order to make possible the communication
    @Autowired
    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // For some GET operation
    // If success, return 200 by default
    @GetMapping
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    // For some GET operation with some ID as a path variable
    // If success, return 200 by default
    @GetMapping("/{id}")
    public Video findById(@PathVariable String id) {
        return videoRepository.findOneById(id);
    }

    // For some POST operation
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Video create(@Valid @RequestBody Video video) {
        return videoRepository.create(video);
    }

    // For some PUT operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Video video, @PathVariable String id) {
        videoRepository.update(video, id);
    }

    // For some Delete operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        videoRepository.delete(id);
    }
}
