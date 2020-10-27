package com.example.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @ClassName VideoMessage
 * @Description:视频消息处理
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoMessage(){

    }
    public VideoMessage(Map<String,String> requestMap, String mediaId,String title,String description){
            super(requestMap);
            this.setMsgType("video");
            this.mediaId=mediaId;
            this.title=title;
            this.description=description;
    }

}
