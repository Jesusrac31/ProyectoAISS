package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.ChannelVM;
import aiss.peertubeminer.service.ChannelService;
import aiss.peertubeminer.etl.TranslationPTtoVMService;
import aiss.peertubeminer.service.VideominerService;
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
    @GetMapping("/{id}")
    public ChannelVM getChannel(@PathVariable String id,
                                @RequestParam(name = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos,
                                @RequestParam(name = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments) { // RequestParam indica que es un parámetro que se pasa como query, sus valores por defecto se ponen en defaultValue
        Channel channelAPI = channelService.getCompleteChannelInfo(id, maxVideos, maxComments);
        return TranslationPTtoVMService.channelTranslation(channelAPI);
    }

    // For some POST operation, it gets the channel from service and post the channel to videominer
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public ChannelVM postChannel(@PathVariable String id,
                                 @RequestParam(name = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos,
                                 @RequestParam(name = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments) { // RequestParam indica que es un parámetro que se pasa como query, sus valores por defecto se ponen en defaultValue
        Channel channelAPI = channelService.getCompleteChannelInfo(id, maxVideos, maxComments);
        ChannelVM channelVM = TranslationPTtoVMService.channelTranslation(channelAPI);
        return videominerService.postChannel(channelVM);
    }
}
