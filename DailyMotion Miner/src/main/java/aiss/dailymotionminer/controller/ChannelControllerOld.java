package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.videominer.ChannelVM;
import aiss.dailymotionminer.repository.ChannelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channels") // The path where you can access to this part of the API
public class ChannelControllerOld {
    private final ChannelRepository channelRepository; // Storage of the info obtained

    // Constructor of the controller, from one repository, it creates the controller in order to make possible the communication
    @Autowired
    public ChannelControllerOld(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    // For some GET operation
    // If success, return 200 by default
    @GetMapping
    public List<ChannelVM> findAll() {
        return channelRepository.findAll();
    }

    // For some GET operation with some ID as a path variable
    // If success, return 200 by default
    @GetMapping("/{id}")
    public ChannelVM findById(@PathVariable String id) {
        return channelRepository.findOneById(id);
    }

    // For some POST operation
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ChannelVM create(@Valid @RequestBody ChannelVM channel) {
        return channelRepository.create(channel);
    }

    // For some PUT operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody ChannelVM channel, @PathVariable String id) {
        channelRepository.update(channel, id);
    }

    // For some Delete operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        channelRepository.delete(id);
    }
}
