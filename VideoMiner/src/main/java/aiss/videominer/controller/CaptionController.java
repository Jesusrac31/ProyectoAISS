package aiss.videominer.controller;

import aiss.videominer.exception.CaptionAlreadyExistsException;
import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
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

@Tag(name = "Caption", description = "Caption management API")
@RestController
@RequestMapping("videominer/api/v1")
public class CaptionController {

    @Autowired
    CaptionRepository captionRepository;
    @Autowired
    VideoRepository videoRepository;

    //Endpoint to find all the captions inside the database
    //GET http://localhost:8080/apipath/captions
    @Operation(summary = "Retrieve a list of captions",
            description = "Get a list of all the Captions in the database",
            tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")
            })
    })
    @GetMapping("/captions")
    public List<Caption> findAll(@Parameter(description = "Page number to retrieve") @RequestParam(defaultValue = "0") int page,
                                 @Parameter(description = "Number of elements per page") @RequestParam(defaultValue = "10") int size,
                                 @Parameter(description = "Filter by exact caption link") @RequestParam(required = false) String link,
                                 @Parameter(description = "Filter by exact caption language") @RequestParam(required = false) String language,
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

        Page<Caption> pageCaptions;
        // Data retrieval
        // If any filter has been specified, our dynamic query will apply them, otherwise all videos will be retrieved
        pageCaptions = captionRepository.findByFilters(link, language, paging);

        return pageCaptions.getContent();
    }

    //Endpoint to find the caption by the id if it is in the database
    //GET http://localhost:8080/apipath/captions/{captionId}
    @Operation(summary = "Retrieve a caption by id",
    description = "Get a caption using its id",
    tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/captions/{captionId}")
    public Caption findOne(@Parameter(description = "id of the caption to be searched")@PathVariable(value = "captionId") String id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepository.findById(id);
        if (caption.isEmpty()){
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    //Endpoint to find all the captions of a video by its ID
    //GET http://localhost:8080/apipath/videos/{videoId}/captions
    @Operation(summary = "Retrieve all captions of a video using its id",
    description = "Get all the captions of a video by its id",
    tags = { "GET" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/videos/{videoId}/captions")
    public List<Caption> getAllCaptionsByVideoId(@Parameter(description = "id of the video whose captions will be retrieved") @PathVariable(value="videoId") String videoId,
                                                 @Parameter(description = "Page number to retrieve") @RequestParam(defaultValue = "0") int page,
                                                 @Parameter(description = "Number of elements per page") @RequestParam(defaultValue = "10") int size,
                                                 @Parameter(description = "Filter by exact caption link") @RequestParam(required = false) String link,
                                                 @Parameter(description = "Filter by exact caption language") @RequestParam(required = false) String language,
                                                 @Parameter(description = "Sorting criteria. Use \"-\" for descending order") @RequestParam(required = false) String order)
            throws VideoNotFoundException {
        Optional<Video> foundVideo = videoRepository.findById(videoId);
        if (foundVideo.isEmpty()) {
            throw new VideoNotFoundException();
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

        Page<Caption> pageCaptions;
        // Data retrieval
        // If any filter has been specified, our dynamic query will apply them, otherwise all videos will be retrieved
        pageCaptions = captionRepository.findByVideoIdAndFilters(videoId, link, language, paging);

        return pageCaptions.getContent();
    }

    // Endpoint to create a caption
    // POST http://localhost:8080/videominer/api/v1/videos/{videoId}/captions
    @Operation(
            summary = "Add caption to video",
            description = "Add a caption to a video by specifying its id",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Caption.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/videos/{videoId}/captions")
    public Caption create(@Parameter(description = "id of the video where the caption will be posted") @PathVariable(value = "videoId") String videoId,
                          @Valid @RequestBody Caption caption) throws VideoNotFoundException, CaptionAlreadyExistsException {
        Optional<Video> videoData = videoRepository.findById(videoId);

        if (videoData.isEmpty()) {
            throw new VideoNotFoundException();
        }
        if (captionRepository.findById(caption.getId()).isPresent()){
            throw new CaptionAlreadyExistsException();
        }

        // Get the actual video object
        Video _video = videoData.get();
        // Insert the new caption in video's captions list
        _video.getCaptions().add(caption);
        // Save the video (Hibernate automatically saves the caption and links the foreign key)
        videoRepository.save(_video);

        return caption;
    }

    // Endpoint to update a caption by its id
    // PUT http://localhost:8080/videominer/api/v1/captions/{captionId}
    @Operation(
            summary = "Update caption",
            description = "Update a caption by specifying its id",
            tags = { "PUT" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/captions/{captionId}")
    public void update(@Parameter(description = "id of the caption to be updated") @PathVariable(value = "captionId") String captionId,
                       @Valid @RequestBody Caption caption) throws CaptionNotFoundException {
        Optional<Caption> captionData = captionRepository.findById(captionId);

        if (captionData.isEmpty()) {
            throw new CaptionNotFoundException();
        }

        Caption _caption = captionData.get();
        _caption.setLanguage(caption.getLanguage());
        _caption.setLink(caption.getLink());
        captionRepository.save(_caption);
    }

    // Endpoint to delete a caption by its id
    // DELETE http://localhost:8080/videominer/api/v1/captions/{captionId}
    @Operation(
            summary = "Delete caption",
            description = "Delete a caption by specifying its id",
            tags = { "DELETE" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/captions/{captionId}")
    public void delete(@Parameter(description = "id of the caption to be deleted") @PathVariable(value = "captionId") String captionId) {
        if (captionRepository.existsById(captionId)) {
            captionRepository.deleteById(captionId);
        }
    }

}
