package aiss.videominer.controller;


@RestController()
@RequestMapping("/videominer/videos")

public class VideoController {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelController(ChannelRepository channelRepository) {this.channelRepository = channelRepository}

    // Get all videos
    @GetMapping
    public List<Channel> findAll() {return channelRepository.findAll();}

    // Get video by id
    @GetMapping("/{id}")
    public Channel findById() {return channelRepository.findOneById(id);}

}

