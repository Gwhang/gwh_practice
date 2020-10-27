package com.example.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @ClassName ImageMessage
 * @Description:图文消息
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage(){

    }
    public ImageMessage(Map<String,String> requestMap, String mediaId){
        super(requestMap);
        this.setMsgType("image");
        this.mediaId=mediaId;


    }

}
