package com.example.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @ClassName MusicMessage
 * @Description:音乐消息
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
    @XStreamAlias("Music")
    private Music music;

    public MusicMessage(Map<String, String> requestMap, Music music) {
        super(requestMap);
        this.setMsgType("music");
        this.music = music;
    }
    public MusicMessage(){

    }
}
