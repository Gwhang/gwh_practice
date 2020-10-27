package com.example.chatroom.pojo;

/**
 * Chat 类用来承载服务器返回给浏览器的消息
 */
public class Chat {

    private String from;//消息从哪来
    private String content;//消息内容
    private String to;//消息到哪去

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
