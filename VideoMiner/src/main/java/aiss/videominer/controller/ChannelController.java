package aiss.videominer.controller;


import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/videominer/api/v1")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    //Endpoint to find all the channels inside the database
    //GET http://localhost:8080/apipath/channels
    @GetMapping
    public List<Channel> findAll() {return channelRepository.findAll();}


    //Endpoint to find the channel by the id if it is in the database
    //GET http://localhost:8080/apipath/channels/{channelId}
    @GetMapping("/{channelId}")
    public Channel findOne(@PathVariable String id)
    {
        Optional<Channel> channel = channelRepository.findById(id);
        if (channel.isEmpty()) {
            return null;
        }
        return channel.get();
    }


    // Endpoint to create a channel
    // POST http://localhost:8080/videominer/api/v1/channels
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel) {
        Channel channel1 = channelRepository.save(
                new Channel(channel.getName(), channel.getDescription(), channel.getCreatedTime())
        );
        return channel1;
    }

    // Endpoint to update a channel
    // PUT http://localhost:8080/videominer/api/v1/channels/{channelId}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping({"/channelId"})
    public void update(@Valid @RequestBody Channel updatedChannel, @PathVariable String id) throws Exception {
        Optional<Channel> channelData = channelRepository.findById(id);

        if (channelData.isEmpty()) {
            throw new Exception();
        }
        Channel channel1 = channelData.get();
        channel1.setName(updatedChannel.getName());
        channel1.setDescription(updatedChannel.getDescription());
        channel1.setCreatedTime(updatedChannel.getCreatedTime());
        channelRepository.save(channel1);
    }

    // Endpoint to delete a channel by its id
    // DELETE http://localhost:8080/videominer/api/v1/channels/{channelId}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/channels/{channelId}"})
    public void delete(@PathVariable String id) {
        if (channelRepository.existsById(id)) {
            channelRepository.deleteById(id);
        }
    }

}