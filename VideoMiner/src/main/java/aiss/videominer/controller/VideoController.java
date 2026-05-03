package aiss.videominer.controller;


import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.exception.VideoAlreadyExistsException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "Video", description = "Video management API")
@RestController
@RequestMapping("videominer/api/v1")
public class VideoController {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    VideoRepository videoRepository;


    //Endpoint to find all the videos inside the database
    //GET http://localhost:8080/videominer/api/v1/videos
    @Operation(
            summary = "Retrieve a list of videos",
            description = "Get a list of videos",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")})
    })
    @GetMapping("/videos")
    public List<Video> findAll(@Parameter(description = "Page number to retrieve") @RequestParam(defaultValue = "0") int page,
                               @Parameter(description = "Number of elements per page") @RequestParam(defaultValue = "10") int size,
                               @Parameter(description = "Filter by exact video name") @RequestParam(required = false) String name,
                               @Parameter(description = "Filter by partial video description") @RequestParam(required = false) String description,
                               @Parameter(description = "Filter by exact release time") @RequestParam(required = false) String releaseTime,
                               @Parameter(description = "Filter by exact username") @RequestParam(required = false) String username,
                               @Parameter(description = "Sorting criteria. Use \"-\" for descending order") @RequestParam(required = false) String order) {
        Pageable paging;
        // Configure sorting and pagination
        if (order != null) {
            if (order.startsWith("-")) {
                // If order starts with "-", sort in descending order
                paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            } else {
                // If not, sort in ascending order
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
            }
        } else {
            // If order parameter is not given, just apply pagination
            paging = PageRequest.of(page, size);
        }

        Page<Video> pageVideos;
        // Data retrieval
        // If any filter has been specified, our dynamic query will apply them, otherwise all videos will be retrieved
        pageVideos = videoRepository.findByFilters(name, description, releaseTime, username, paging);

        return pageVideos.getContent();
    }

    //Endpoint to find the video by the id if it is in the database
    //GET http://localhost:8080/videominer/api/v1/videos/{videoId}

    @Operation(
            summary = "Retrieve video by id",
            description = "Get a video by specifying its id",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/videos/{videoId}")
    public Video findOne(@Parameter(description = "id of the video to be searched") @PathVariable(value = "videoId") String id)
            throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(id);

        if (video.isEmpty()) {
            throw new VideoNotFoundException();
        }

        return video.get();
    }

    //Endpoint to find all the videos from a channel inside the database
    //GET http://localhost:8080/videominer/api/v1/channels/{channelId}/videos
    @Operation(
            summary = "Retrieve channel videos",
            description = "Get the list of videos of a channel by specifying its id",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/channels/{channelId}/videos")
    public List<Video> getAllVideosByChannelId(@Parameter(description = "id of the channel whose videos are retrieved") @PathVariable(value = "channelId") String channelId,
                                               @Parameter(description = "Page number to retrieve") @RequestParam(defaultValue = "0") int page,
                                               @Parameter(description = "Number of elements per page") @RequestParam(defaultValue = "10") int size,
                                               @Parameter(description = "Filter by exact channel name") @RequestParam(required = false) String name,
                                               @Parameter(description = "Filter by partial channel description") @RequestParam(required = false) String description,
                                               @Parameter(description = "Filter by exact release time") @RequestParam(required = false) String releaseTime,
                                               @Parameter(description = "Filter by exact username") @RequestParam(required = false) String username,
                                               @Parameter(description = "Sorting criteria. Use \"-\" for descending order") @RequestParam(required = false) String order)
            throws ChannelNotFoundException {
        Optional<Channel> channelData = channelRepository.findById(channelId);

        if (channelData.isEmpty()) {
            throw new ChannelNotFoundException();
        }

        Pageable paging;
        // Configure sorting and pagination
        if (order != null) {
            if (order.startsWith("-")) {
                // If order starts with "-", sort in descending order
                paging = PageRequest.of(page, size, Sort.by(order.substring(1)).descending());
            } else {
                // If not, sort in ascending order
                paging = PageRequest.of(page, size, Sort.by(order).ascending());
            }
        } else {
            // If order parameter is not given, just apply pagination
            paging = PageRequest.of(page, size);
        }

        Page<Video> pageVideos;
        // Data retrieval
        // If any filter has been specified, our dynamic query will apply them, otherwise all videos will be retrieved
        pageVideos = videoRepository.findByChannelIdAndFilters(channelId, name, description, releaseTime, username, paging);
        return pageVideos.getContent();
    }

    // Endpoint to create a video
    // POST http://localhost:8080/videominer/api/v1/channels/{channelId}/videos
    @Operation(
            summary = "Add video to channel",
            description = "Add a video to a channel by specifying its id",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Video.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/channels/{channelId}/videos")
    public Video create(@Parameter(description = "id of the channel where the video will be posted") @PathVariable(value = "channelId") String channelId,
                        @Valid @RequestBody Video video) throws ChannelNotFoundException, VideoAlreadyExistsException {
        Optional<Channel> channelData = channelRepository.findById(channelId);

        if (channelData.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        if (videoRepository.findById(video.getId()).isPresent()){
            throw new VideoAlreadyExistsException();
        }

        // Save video in video Repository
        Video createdVideo = videoRepository.save(video);

        // Associate video to the channel
        Channel _channel = channelData.get();
        List<Video> videos = _channel.getVideos(); // Get list of videos
        videos.add(createdVideo); // Insert the new video
        _channel.setVideos(videos); // Refresh the list of videos
        channelRepository.save(_channel); // Update channel data

        return createdVideo;
    }

    // Endpoint to update a video
    // PUT http://localhost:8080/videominer/api/v1/videos/{videoId}
    @Operation(
            summary = "Update video",
            description = "Update a video by specifying its id",
            tags = { "PUT" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping({"/videos/{videoId}"})
    public void update(@Parameter(description = "id of the video to be updated") @PathVariable(value = "videoId") String id,
                       @Valid @RequestBody Video updatedVideo) throws VideoNotFoundException {
        Optional<Video> videoData = videoRepository.findById(id);

        if (videoData.isEmpty()) {
            throw new VideoNotFoundException();
        }
        Video video1 = videoData.get();
        video1.setName(updatedVideo.getName());
        video1.setDescription(updatedVideo.getDescription());
        video1.setReleaseTime(updatedVideo.getReleaseTime());
        video1.setUser(updatedVideo.getUser());
        videoRepository.save(video1);
    }

    // Endpoint to delete a video by its id
    // DELETE http://localhost:8080/videominer/api/v1/videos/{videoId}
    @Operation(
            summary = "Delete video",
            description = "Delete a video by specifying its id",
            tags = { "DELETE" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/videos/{videoId}"})
    public void delete(@Parameter(description = "id of the video to be deleted") @PathVariable(value = "videoId") String id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
        }
    }
}

