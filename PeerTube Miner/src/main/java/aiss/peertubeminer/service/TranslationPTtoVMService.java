package aiss.peertubeminer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import aiss.peertubeminer.model.peertube.Account;
import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.videominer.ChannelVM;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.CaptionVM;
import aiss.peertubeminer.model.videominer.CommentVM;
import aiss.peertubeminer.model.videominer.UserVM;
import aiss.peertubeminer.model.videominer.VideoVM;


public class TranslationPTtoVMService{

    // Clarifications:
    //VM: VideoMiner
    //PT: PeerTube

    public static ChannelVM channelTranslation(Channel channelPT){
        // Creation of Channel for VM
        ChannelVM resChannelVM = new ChannelVM(channelPT.getId(), channelPT.getName(), channelPT.getDescription(), channelPT.getCreatedAt());
        // Iterating over videos of PT, translating them to videos of VM and saving them into an auxiliary list of videos. 
        List<VideoVM> auxVideoList = new ArrayList<VideoVM>();
        for (Video vd: channelPT.getVideos()){
            auxVideoList.add(videoTransaltion(vd));
        }
        // Setting the list of videos of the channel
        resChannelVM.setVideos(auxVideoList);
        return resChannelVM;
    }

    public static VideoVM videoTransaltion(Video videoPT){  // Translation from PT to VM model of Video
    // Creation of Video model for VideoMiner
    VideoVM resVideoVM = new VideoVM(videoPT.getId(), videoPT.getName(), videoPT.getDescription(), videoPT.getPublishedAt());
    // Translating Accoutn (PT) to User (VM)
    //Setting the author of resVideoVM
    resVideoVM.setAuthor(userTranslation(videoPT.getAccount()));
    //Iteratig over the comments of videoPT and adding the comments to resVideoVM one by one
    List<CommentVM> auxCommentList = new ArrayList<CommentVM>();
    for (Comment cm: videoPT.getComments()){
        auxCommentList.add(commentTranslation(cm));
    }
    // Setting the resulting list of comments as comments of the resVideoVM
    resVideoVM.setComments(auxCommentList);
    // Iterating over subtitles of videoPT, translating from PT to VM model one by one, adding to an 
    // auxiliary list and setting the result as captions list of resVideoVM
    List<CaptionVM> auxCaptionsList = new ArrayList<CaptionVM>();
    for (Caption cp: videoPT.getCaptions()){
        auxCaptionsList.add(captionTranslation(cp));
    }
    resVideoVM.setCaptions(auxCaptionsList);

    return resVideoVM;
    }

    public static UserVM userTranslation(Account accountPT){ // Translation from Account (PT) to User (VM) model
    //ATTENTION: in PT the model Account contains a LIST of avatars, however the model of VM only needs a link to the avatar. We took the link of the first avatar.
    UserVM resUserVM = new UserVM(Long.valueOf(accountPT.getId()), accountPT.getName(), accountPT.getUrl(), accountPT.getAvatars().getFirst().getFileUrl());
    return resUserVM;
    }

    public static CaptionVM captionTranslation(Caption captionPT){  //Caption translation
    // ATTENTION: captions returned by the PeerTube API do not contain an ID, so for the VideoMiner model of captions
    // a random ID is set 
    CaptionVM resCaptionVM = new CaptionVM(UUID.randomUUID().toString(), captionPT.getFileUrl(), captionPT.getLanguage().getLabel());
    return resCaptionVM;
    }

    public static CommentVM commentTranslation(Comment commentPT){  //Comment translation
    CommentVM resCommentVM = new CommentVM(commentPT.getId(), commentPT.getText(), commentPT.getCreatedAt());
    return resCommentVM;
    }

}