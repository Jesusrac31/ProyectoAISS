package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.ChannelVM;
import aiss.peertubeminer.service.peertube.ChannelService;
import aiss.peertubeminer.etl.TranslationPTtoVMService;
import aiss.peertubeminer.service.VideominerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peertube") // The path where you can access to this part of the API
public class ChannelController {
    private final ChannelService channelService; // Storage of the info obtained
    private final VideominerService videominerService;

    // Constructor of the controller, from one repository, it creates the controller in order to make possible the communication
    @Autowired
    public ChannelController(ChannelService channelService, VideominerService videominerService) {
        this.channelService = channelService;
        this.videominerService = videominerService;
    }


    // For some GET operation with some ID as a path variable, it gets the channel and returns directly
    // If success, return 200 by default
    @Operation(
            summary = "Retrieve one channel",
            description = "Get one channel from DailyMotion",
            tags = { "GET" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ChannelVM.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public ChannelVM getChannel(@PathVariable String id,
                                @RequestParam(name = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos, // RequestParam indica que es un parámetro que se pasa como query, sus valores por defecto se ponen en defaultValue
                                @RequestParam(name = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments) {
            Channel channelAPI = channelService.getCompleteChannelInfo(id, maxVideos, maxComments);
            return TranslationPTtoVMService.channelTranslation(channelAPI);
    }

    // For some POST operation, it gets the channel from service and post the channel to videominer
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    @Operation(
            summary = "Retrieve one channel",
            description = "Send one channel from DailyMotion to videominer",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ChannelVM.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema()) })
    })
    public ChannelVM postChannel(@PathVariable String id,
                                 @RequestParam(name = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos, // RequestParam indica que es un parámetro que se pasa como query, sus valores por defecto se ponen en defaultValue
                                 @RequestParam(name = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments) {
            Channel channelAPI = channelService.getCompleteChannelInfo(id, maxVideos, maxComments);
            ChannelVM channelVM = TranslationPTtoVMService.channelTranslation(channelAPI);
            return videominerService.postChannel(channelVM);
    }
}
