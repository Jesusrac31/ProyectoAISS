package aiss.videominer.controller;



@RestController()
@RequestMapping("/videominer/channels")
public class ChannelController {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelController(ChannelRepository channelRepository) {this.channelRepository = channelRepository}

    // Get list of channels
    @GetMapping
    public List<Channel> findAll() {return channelRepository.findAll();}

    // Add channels
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel) { return channelRepository.create(channel);}

    // Post to get a channel, and add new channel data



}