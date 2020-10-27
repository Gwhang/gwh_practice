package com.example.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @ClassName VoiceMessage
 * @Description:语音消息
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public VoiceMessage(){

    }

    public VoiceMessage(Map<String,String> requestMap, String mediaId){
        super(requestMap);
        this.setMsgType("voice");
        this.mediaId=mediaId;
    }


}

