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

    public static ChannelVM channelTranslation(Channel channelPT){
        //First: Creation of Channel for VM
        ChannelVM resChannelVM = new ChannelVM(channelPT.getId(), channelPT.getName(), channelPT.getDescription(), channelPT.getCreatedAt());
        //Second: I have to translate videos from PT to videos of VM
        List<VideoVM> auxVideoList = new ArrayList<VideoVM>();
        for (Video vd: channelPT.getVideos()){
            auxVideoList.add(videoTransaltion(vd));
        }
        resChannelVM.setVideos(auxVideoList);
        return resChannelVM;
    }

    public static VideoVM videoTransaltion(Video videoPT){
    VideoVM resVideoVM = new VideoVM(videoPT.getId(), videoPT.getName(), videoPT.getDescription(), videoPT.getPublishedAt());
    // Third: I have to translate Accoutn (PT) to User (VM)
    resVideoVM.setAuthor(userTranslation(videoPT.getAccount()));
    // Fourth: we need a comment translation
    //After we will iterate over the comments of videoPT and add the comments to resVideoVM one by one
    List<CommentVM> auxCommentList = new ArrayList<CommentVM>();
    for (Comment cm: videoPT.getComments()){
        auxCommentList.add(commentTranslation(cm));
    }
    resVideoVM.setComments(auxCommentList);
    //Fifth: we need captions translation
    //The same, iterate over subtitles of videoPT, translate one by one and add to resVideoVM one by one
    List<CaptionVM> auxCaptionsList = new ArrayList<CaptionVM>();
    for (Caption cp: videoPT.getCaptions()){
        auxCaptionsList.add(captionTranslation(cp));
    }
    resVideoVM.setCaptions(auxCaptionsList);

    return resVideoVM;
    }

    public static UserVM userTranslation(Account accountPT){ // Translation from Account (PT) to User (VM)
    //ATTENTION: in PT the model Account contains a LIST of avatars, however the model of VM only needs a link to the avatar. I took the link of the first avatar.
    UserVM resUserVM = new UserVM(Long.valueOf(accountPT.getId()), accountPT.getName(), accountPT.getUrl(), accountPT.getAvatars().getFirst().getFileUrl());
    return resUserVM;
    }

    public static CaptionVM captionTranslation(Caption captionPT){  //Caption translation
    CaptionVM resCaptionVM = new CaptionVM(UUID.randomUUID().toString(), captionPT.getFileUrl(), captionPT.getLanguage().getLabel());
    return resCaptionVM;
    }

    public static CommentVM commentTranslation(Comment commentPT){  //Comment translation
    CommentVM resCommentVM = new CommentVM(commentPT.getId(), commentPT.getText(), commentPT.getCreatedAt());
    return resCommentVM;
    }

}