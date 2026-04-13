package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.videominer.Comment;
import aiss.peertubeminer.repository.CommentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments") // The path where you can access to this part of the API
public class CommentController {
    private final CommentRepository commentRepository; // Storage of the info obtained

    // Constructor of the controller, from one repository, it creates the controller in order to make possible the communication
    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // For some GET operation
    // If success, return 200 by default
    @GetMapping
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    // For some GET operation with some ID as a path variable
    // If success, return 200 by default
    @GetMapping("/{id}")
    public Comment findById(@PathVariable String id) {
        return commentRepository.findOneById(id);
    }

    // For some POST operation
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Comment create(@Valid @RequestBody Comment comment) {
        return commentRepository.create(comment);
    }

    // For some PUT operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Comment comment, @PathVariable String id) {
        commentRepository.update(comment, id);
    }

    // For some Delete operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        commentRepository.delete(id);
    }
}
