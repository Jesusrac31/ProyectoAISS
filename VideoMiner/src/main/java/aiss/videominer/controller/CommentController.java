package aiss.videominer.controller;

import aiss.videominer.exception.CaptionAlreadyExistsException;
import aiss.videominer.exception.CommentAlreadyExistsException;
import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Comment", description = "Comment management API")
@RestController
@RequestMapping("videominer/api/v1")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    VideoRepository videoRepository;

    // GET http://localhost:8080/videominer/api/v1/comments
    @Operation(
            summary = "Retrieve a list of comments",
            description = "Get a list of comments",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")})
    })
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // GET http://localhost:8080/videominer/api/v1/comments/{commentId}
    @Operation(
            summary = "Retrieve comment by id",
            description = "Get a comment by specifying its id",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/comments/{commentId}")
    public Comment findOne(@Parameter(description = "id of the comment to be searched") @PathVariable(value = "commentId") String commentId)
    throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (!comment.isPresent()) {
            throw new CommentNotFoundException();
        }

        return comment.get();
    }

    // GET http://localhost:8080/videominer/api/v1/videos/{videoId}/comments
    @Operation(
            summary = "Retrieve video comments",
            description = "Get the list of comments of a video by specifying its id",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/videos/{videoId}/comments")
    public List<Comment> getAllCommentsByVideoId(@Parameter(description = "id of the video whose comments are retrieved") @PathVariable(value = "videoId") String videoId)
            throws VideoNotFoundException {
        Optional<Video> video = videoRepository.findById(videoId);

        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }

        return video.get().getComments();
    }

    // POST http://localhost:8080/videominer/api/v1/videos/{videoId}/comments
    @Operation(
            summary = "Add comment to video",
            description = "Add a comment to a video by specifying its id",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/videos/{videoId}/comments")
    public Comment create(@Parameter(description = "id of the video where the comment will be posted") @PathVariable(value = "videoId") String videoId,
                          @Valid @RequestBody Comment comment) throws VideoNotFoundException, CommentAlreadyExistsException {
        Optional<Video> video = videoRepository.findById(videoId);

        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }
        if (commentRepository.findById(comment.getId()).isPresent()){
            throw new CommentAlreadyExistsException();
        }

        video.get().getComments().add(comment);
        return commentRepository.save(comment);
    }

    // PUT http://localhost:8080/videominer/api/v1/comments/{commentId}
    @Operation(
            summary = "Update comment",
            description = "Update a comment by specifying its id",
            tags = { "PUT" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/comments/{commentId}")
    public void update(@Parameter(description = "id of the comment to be updated") @PathVariable(value = "commentId") String commentId,
                          @Valid @RequestBody Comment comment) throws CommentNotFoundException {
        Optional<Comment> commentData = commentRepository.findById(commentId);

        if (!commentData.isPresent()) {
            throw new CommentNotFoundException();
        }

        Comment _comment = commentData.get();
        _comment.setText(comment.getText());
        _comment.setCreatedOn(comment.getCreatedOn());
        commentRepository.save(_comment);
    }

    // DELETE http://localhost:8080/videominer/api/v1/comments/{commentId}
    @Operation(
            summary = "Delete comment",
            description = "Delete a comment by specifying its id",
            tags = { "DELETE" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/comments/{commentId}")
    public void delete(@Parameter(description = "id of the comment to be deleted") @PathVariable(value = "commentId") String commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
        }
    }
}
