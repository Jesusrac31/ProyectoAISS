package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.etl.TranslationDMtoVMService;
import aiss.dailymotionminer.model.videominer.ChannelVM;
import aiss.dailymotionminer.service.VideominerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import aiss.dailymotionminer.model.dailymotion.Channel;

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
    @GetMapping("/{channelId}")
    public ChannelVM getChannel(@PathVariable(value = "channelId") String id,
                                @RequestParam(name = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos,
                                @RequestParam(name = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments){
        Channel channelAPI = channelService.getCompleteChannelInfo(id, maxVideos, maxComments); // ATTENTION! This function, service, and everything related to this line must be changed since the necessary classes are still not created
        return TranslationDMtoVMService.channelTranslation(channelAPI); // ATTENTION! The method name is unknown and probably must be changed
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{channelId}")
    public ChannelVM postChannel(@PathVariable(value = "channelId") String channelId,
                                 @RequestParam(name = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos,
                                 @RequestParam(name = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments){
        Channel channelAPI = channelService.getCompleteChannelInfo(id, maxVideos, maxComments); // ATTENTION! The name of the classes/methods can change
        ChannelVM channelVM = TranslationDMtoVMService.channelTranslation(channelAPI);
        return videominerService.postChannel(channelVM);
    }
}
