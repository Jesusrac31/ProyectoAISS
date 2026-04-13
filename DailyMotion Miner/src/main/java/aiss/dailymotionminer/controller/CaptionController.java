package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.videominer.Caption;
import aiss.peertubeminer.repository.CaptionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/captions") // The path where you can access to this part of the API
public class CaptionController {
    private final CaptionRepository captionRepository; // Storage of the info obtained

    // Constructor of the controller, from one repository, it creates the controller in order to make possible the communication
    @Autowired
    public CaptionController(CaptionRepository captionRepository) {
        this.captionRepository = captionRepository;
    }

    // For some GET operation
    // If success, return 200 by default
    @GetMapping
    public List<Caption> findAll() {
        return captionRepository.findAll();
    }

    // For some GET operation with some ID as a path variable
    // If success, return 200 by default
    @GetMapping("/{id}")
    public Caption findById(@PathVariable String id) {
        return captionRepository.findOneById(id);
    }

    // For some POST operation
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Caption create(@Valid @RequestBody Caption caption) {
        return captionRepository.create(caption);
    }

    // For some PUT operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Caption caption, @PathVariable String id) {
        captionRepository.update(caption, id);
    }

    // For some Delete operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        captionRepository.delete(id);
    }
}
