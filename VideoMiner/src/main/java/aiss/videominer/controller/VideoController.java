package aiss.videominer.controller;


import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("/videominer/videos")
public class VideoController {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    VideoRepository videoRepository;


    //Endpoint to find all the videos inside the database
    //GET http://localhost:8080/apipath/videos
    @GetMapping
    public List<Video> findAll() {return videoRepository.findAll();}

    //Endpoint to find the video by the id if it is in the database
    //GET http://localhost:8080/apipath/videos/
    @GetMapping("/{videoId}")
    public Video findOne(@PathVariable String id)
    {
        Optional<Video> video = videoRepository.findById(id);
        if (video.isEmpty()) {
            return null;
        }
        return video.get();
    }
    //Endpoint to find all the videos from a channel inside the database
    //GET http://localhost:8080/apipath/channels/{channelId}/videos
    @GetMapping("/channels/{channelId}/videos")
    public List<Video> getAllVideosByChannelId(@PathVariable String channelId)
            throws Exception {
        Optional<Channel> channelData = channelRepository.findById(channelId);

        if (!channelData.isPresent()) {
            throw new VideoNotFoundException();
        }

        return channelData.get().getVideos();
    }

    // Endpoint to create a video
    // POST http://localhost:8080/videominer/api/v1/videos
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Video create(@Valid @RequestBody Video video) {
        Video video1 = videoRepository.save(new Video(video.getName(), video.getDescription(), video.getReleaseTime(), video.getAuthor()));
        return video1;
    }

    // Endpoint to update a video
    // PUT http://localhost:8080/videominer/api/v1/videos/{videoId}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping({"/videoId"})
    public void update(@Valid @RequestBody Video updatedVideo, @PathVariable String id) throws Exception {
        Optional<Video> videoData = videoRepository.findById(id);

        if (videoData.isEmpty()) {
            throw new Exception();
        }
        Video video1 = videoData.get();
        video1.setName(updatedVideo.getName());
        video1.setDescription(updatedVideo.getDescription());
        video1.setReleaseTime(updatedVideo.getReleaseTime());
        video1.setAuthor(updatedVideo.getAuthor());
        videoRepository.save(video1);
    }

    // Endpoint to delete a video by its id
    // DELETE http://localhost:8080/videominer/api/v1/videos/{videoId}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/videos/{videoId}"})
    public void delete(@PathVariable String id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
        }
    }
}

