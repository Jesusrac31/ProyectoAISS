package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Account;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.ChannelVM;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.UserVM;
import aiss.peertubeminer.model.videominer.VideoVM;


public class TranslationPTtoVMService{

    public static ChannelVM channelTranslation(Channel channelPT){
        // String id, String name, String description, String createdTime
        //First: Creation of Channel for VM
        ChannelVM resChannelVM = new ChannelVM(channelPT.getId().toString(), channelPT.getName(), channelPT.getDescription(), channelPT.getCreatedAt());
//        resVM.setVideos(channelPT.getVideos());
        //Second: I have to translate videos from PT to videos of VM
    }

    public static VideoVM videoTransaltion(Video videoPT){

        // PT:

    // @JsonProperty("id")
    // private Integer id;
    // @JsonProperty("name")
    // private String name;
    // @JsonProperty("description")
    // private String description;
    // @JsonProperty("publishedAt")
    // private String publishedAt;
    // @JsonProperty("account")
    // private Account account;
    // @JsonIgnore
    // private List<Caption> captions;
    // @JsonIgnore
    // private List<Comment> comments;


        // VM:
    //         private String id;
    // private String name;
    // private String description;
    // private String releaseTime;
    // private UserVM author;
    // private List<CommentVM> comments;
    // private List<CaptionVM> captions;
    
    // public VideoVM(String id, String name, String description, String releaseTime) {
    //     this.id = id;
    //     this.name = name;
    //     this.description = description;
    //     this.releaseTime = releaseTime;
    //     this.author = null;
    //     this.comments = new ArrayList<>();
    //     this.captions = new ArrayList<>();
    // }

    // Third: I have to translate Accoutn (PT) to User (VM)
    VideoVM resVideoVM = new VideoVM(videoPT.getId().toString(), videoPT.getName(), videoPT.getDescription(), videoPT.getPublishedAt());
    resVideoVM.setAuthor(userTranslation(videoPT.getAccount()));
    // Fourth: we need a comment translation
    //After we will iterate over the comments of videoPT and add the comments to resVideoVM one by one
    //Fifth: we need captions translation
    //The same, iterate over subtitles of videoPT, translate one by one and add to resVideoVM one by one

    }

    public static UserVM userTranslation(Account accountPT){

        // PT:

    //     @JsonProperty("id")
    // private Integer id;
    // @JsonProperty("name")
    // private String name;
    // @JsonProperty("url")
    // private String url;
    // @JsonProperty("avatars")
    // private List<Avatar> avatars;

    // VM: 

    // private Long id;
    // private String name;
    // private String user_link;
    // private String picture_link;


    //ATTENTION: in PT the model Account contains a LIST of avatars, however the model of VM only needs a link to the avatar. I took the link of the first avatar.
    UserVM resUserVM = new UserVM((long)accountPT.getId(), accountPT.getName(), accountPT.getUrl(), accountPT.getAvatars().getFirst().getFileUrl());
    return resUserVM;

    }

}