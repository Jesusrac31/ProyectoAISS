package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.etl.TranslationDMtoVMService;
import aiss.dailymotionminer.model.videominer.ChannelVM;
import aiss.dailymotionminer.service.Dailymotion.ChannelService;
import aiss.dailymotionminer.service.VideominerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import aiss.dailymotionminer.model.Dailymotion.Channel;

@Tag(name = "Channel", description = "Channel managing in DailyMotion")
@RestController
@RequestMapping("/dailymotion")
public class ChannelController {
    // (1!!!)
    private final ChannelService channelService; // The name depends on the Services that will be created in the future
    private final VideominerService videominerService;


    // Constructor of the controller is created. Instantiates service classes which allow to communicate with the APIs
    @Autowired
    public ChannelController(ChannelService channelService, VideominerService videominerService){ // (1!!!)
        this.channelService = channelService;
        this.videominerService= videominerService;
    }

    // GET operation for the channel given its ID
    // If success, returns 200 by default
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
    @GetMapping("/{channelId}")
    public ChannelVM getChannel(@PathVariable(value = "channelId") String id,
                                @RequestParam(name = "maxVideos", defaultValue = "${dailymotionminer.maxVideos}") Integer maxVideos,
                                @RequestParam(name = "maxComments", defaultValue = "${dailymotionminer.maxTags}") Integer maxComments,
                                @RequestParam(name = "maxPages", defaultValue = "${dailymotionminer.maxPages}") Integer maxPages) {
            Channel channelAPI = channelService.getCompleteChannel(id, maxVideos, maxComments, maxPages); // ATTENTION! This function, service, and everything related to this line must be changed since the necessary classes are still not created
            return TranslationDMtoVMService.channelTranslation(channelAPI); // ATTENTION! The method name is unknown and probably must be changed
    }

    @Operation(
            summary = "Send one channel to videominer",
            description = "Send one channel from DailyMotion to videominer",
            tags = { "POST" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ChannelVM.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema()) })
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelId}")
    public ChannelVM postChannel(@PathVariable(value = "channelId") String id,
                                 @RequestParam(name = "maxVideos", defaultValue = "${dailymotionminer.maxVideos}") Integer maxVideos,
                                 @RequestParam(name = "maxComments", defaultValue = "${dailymotionminer.maxTags}") Integer maxComments,
                                 @RequestParam(name = "maxPages", defaultValue = "${dailymotionminer.maxPages}") Integer maxPages) {
            Channel channelAPI = channelService.getCompleteChannel(id, maxVideos, maxComments, maxPages); // ATTENTION! The name of the classes/methods can change
            ChannelVM channelVM = TranslationDMtoVMService.channelTranslation(channelAPI);
            return videominerService.postChannel(channelVM);
    }
}
