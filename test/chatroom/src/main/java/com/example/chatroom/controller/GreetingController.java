package com.example.chatroom.controller;

import com.example.chatroom.pojo.Chat;
import com.example.chatroom.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * 群聊控制类
 */
@Controller
public class GreetingController {

    //SimpMessagingTemplate这个类主要是实现向浏览器发送消息的功能。
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 这个方法用来处理浏览器发送来的消息，对其进行处理
     * @param message
     * @return
     */
    //@MessageMapping 类似 @RequestMapping
    @MessageMapping("/hello")
    //处理完之后对其进行转发到 SendTo 中的路径
    @SendTo("/topic/greetings")
    public Message greeting(Message message) {
        return message;
    }

    /**
     * （1）SimpMessagingTemplate这个类主要是实现向浏览器发送消息的功能。
     * （2）在Spring MVC中，可以直接在参数中获取Principal，Principal中包含有当前用户的用户名。
     * （3）convertAndSendToUser方法是向用户发送一条消息，第一个参数是目标用户用户名，第二个参数是浏览器中订阅消息的地址，第三个参数是消息本身。
     * @param principal
     * @param chat
     */
    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat) {
        //消息从哪来
        chat.setFrom(principal.getName());
        //要发给谁，要发的地址，要发的对象
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
}
