package aiss.videominer.controller;

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apipath")
public class CaptionController {

//    It shall implement, at a minimum, several read operations to list all captions (DONE),
//    search by id, and return the captions for a video given its id.

    @Autowired
    CaptionRepository captionRepository;
    @Autowired
    VideoRepository videoRepository;

    //Function to find all the captions inside the database
    //GET http://localhost:8080/apipath/captions
    @GetMapping
    public List<Caption> findAll(){
        return captionRepository.findAll();
    }

    //Function to find the caption by the id if it is in the database
    //GET http://localhost:8080/apipath/captions/{captionId}
    @GetMapping("/captions/{captionId}")
    public Caption findOne(@PathVariable(value = "captionId") String id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepository.findById(id);
        if (!caption.isPresent()){
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    //Function to find all the captions of a video by its ID
    //GET http://localhost:8080/apipath/videos/{videoId}/captions
    @GetMapping("/videos/{videoId}/captions")
    public List<Caption> getAllCaptionsByVideoId(@PathVariable(value="videoId") String videoId) throws VideoNotFoundException {
        Optional<Video> foundVideo = videoRepository.findById(videoId);
        if (!foundVideo.isPresent()) {
            throw new VideoNotFoundException();
        }
        List<Caption> result = new ArrayList<Caption>(foundVideo.get().getCaptions());
        return result;
    }



}
