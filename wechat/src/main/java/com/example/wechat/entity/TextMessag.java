package com.example.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @ClassName TextMessag
 * @Description:文本消息回复
 * @Author guanWanHang
 * @Date 2020/7/25
 * @Version V1.0
 **/
@XStreamAlias("xml")
public class TextMessag extends BaseMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }

    public TextMessag(Map<String,String> requestMap,String content) {
        super(requestMap);
        this.setMsgType("text");
        this.content=content;
    }
    public TextMessag() {
    }



}
