package aiss.videominer.controller;


import aiss.videominer.exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
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

@Tag(name = "Channel", description = "Channel management API")
@RestController
@RequestMapping("videominer/api/v1")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    //Endpoint to find all the channels inside the database
    //GET http://localhost:8080/videominer/api/v1/channels
    @Operation(
            summary = "Retrieve a list of channels",
            description = "Get a list of channels",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")})
    })
    @GetMapping("/channels")
    public List<Channel> findAll() { return channelRepository.findAll();}


    //Endpoint to find the channel by the id if it is in the database
    //GET http://localhost:8080/videominer/api/v1/channels/{channelId}
    @Operation(
            summary = "Retrieve channel by id",
            description = "Get a channel by specifying its id",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/channels/{channelId}")
    public Channel findOne(@Parameter(description = "id of the channel to be searched") @PathVariable(value = "channelId") String id)
            throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepository.findById(id);

        if (!channel.isPresent()) {
            throw new ChannelNotFoundException();
        }

        return channel.get();
    }


    // Endpoint to create a channel
    // POST http://localhost:8080/videominer/api/v1/channels
    @Operation(
            summary = "Add a new channel",
            description = "Create a new channel with the data sent in the body of the request",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Channel.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/channels")
    public Channel create(@Valid @RequestBody Channel channel) {
        return channelRepository.save(channel);
    }

    // Endpoint to update a channel
    // PUT http://localhost:8080/videominer/api/v1/channels/{channelId}
    @Operation(
            summary = "Update a channel",
            description = "Update a channel by specifying its id",
            tags = { "PUT" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping({"/channels/{channelId}"})
    public void update(@Valid @RequestBody Channel updatedChannel, @Parameter(description = "id of the channel to be updated") @PathVariable(value = "channelId") String id)
            throws ChannelNotFoundException {
        Optional<Channel> channelData = channelRepository.findById(id);

        if (!channelData.isPresent()) {
            throw new ChannelNotFoundException();
        }

        Channel channel1 = channelData.get();
        channel1.setName(updatedChannel.getName());
        channel1.setDescription(updatedChannel.getDescription());
        channel1.setCreatedTime(updatedChannel.getCreatedTime());
        channelRepository.save(channel1);
    }

    // Endpoint to delete a channel by its id
    // DELETE http://localhost:8080/videominer/api/v1/channels/{channelId}
    @Operation(
            summary = "Delete a channel",
            description = "Delete a channel by specifying its id",
            tags = { "DELETE" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"/channels/{channelId}"})
    public void delete(@Parameter(description = "id of the channel to be deleted") @PathVariable(value = "channelId") String id) {
        if (channelRepository.existsById(id)) {
            channelRepository.deleteById(id);
        }
    }

}