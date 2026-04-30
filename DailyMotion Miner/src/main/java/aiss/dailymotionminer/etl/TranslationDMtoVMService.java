package aiss.dailymotionminer.etl;


import aiss.dailymotionminer.model.Dailymotion.*;
import aiss.dailymotionminer.model.videominer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Translation from DailyMotion to VideoMiner
public class TranslationDMtoVMService{


    // Clarifications:
    //VM: VideoMiner
    //DM: Dailymotion

    public static ChannelVM channelTranslation(Channel channelDM){
        // Creation of Channel for VM
        ChannelVM resChannelVM = new ChannelVM(channelDM.getId(), channelDM.getName(), channelDM.getDescription(), channelDM.getCreatedAt());
        // Iterating over videos of PT, translating them to videos of VM and saving them into an auxiliary list of videos.
        List<VideoVM> auxVideoList = new ArrayList<>();
        for (Video vd: channelDM.getVideos()){
            auxVideoList.add(videoTranslation(vd));
        }
        // Setting the list of videos of the channel
        resChannelVM.setVideos(auxVideoList);
        return resChannelVM;
    }

    public static VideoVM videoTranslation(Video videoDM){  // Translation from PT to VM model of Video
        // Creation of Video model for VideoMiner
        VideoVM resVideoVM = new VideoVM(videoDM.getId(), videoDM.getTitle(), videoDM.getDescription(), videoDM.getCreated_time());

        //Setting the author of resVideoVM
        resVideoVM.setAuthor(userTranslation(videoDM.getOwner()));

        //Iterating over the comments of videoPT and adding the comments to resVideoVM one by one
        List<CommentVM> auxCommentList = new ArrayList<>();
     //   for (Tag tag: videoDM.getTags()){
     //       auxCommentList.add(commentTranslation(tag));
        // }
        // Setting the resulting list of comments as comments of the resVideoVM
        resVideoVM.setComments(auxCommentList);
        // Iterating over subtitles of videos, translating from DM to VM model one by one, adding to an
        // auxiliary list and setting the result as captions list of resVideoVM
        List<CaptionVM> auxCaptionsList = new ArrayList<>();
        for (Subtitle cp: videoDM.getSubtitles()){
            auxCaptionsList.add(captionTranslation(cp));
        }
        resVideoVM.setCaptions(auxCaptionsList);

    return resVideoVM;
    }

    public static UserVM userTranslation(Owner ownerDM){ // Translation from Owner (DM) to User (VM) model
        return new UserVM(ownerDM.getId(), ownerDM.getScreenname(), ownerDM.getUrl(), ownerDM.getAvatar_link());
    }

    public static CaptionVM captionTranslation(Subtitle subtitleDM){  //Caption translation
        // ATTENTION: captions returned by the PeerTube API do not contain an ID, so for the VideoMiner model of captions
        // a random ID is set
        return new CaptionVM(UUID.randomUUID().toString(), subtitleDM.getUrl(), subtitleDM.getLanguage());
    }

    public static CommentVM commentTranslation(Tag tagDM) {
        // Right now, I put the tag as a comment
        CommentVM resCommentVM = new CommentVM(tagDM.getId(), null, tagDM.getCreatedOn());
        return resCommentVM;
    }
}
