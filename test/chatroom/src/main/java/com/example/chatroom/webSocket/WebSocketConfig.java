package com.example.chatroom.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置 WebSocketConfig
 */
@Configuration
//开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //定义消息代理的前缀，topic 表示群聊，queue 表示单聊
        registry.enableSimpleBroker("/topic","/queue");
        //配置一个或者多个前缀，过滤出需要代理方法处理的消息
        registry.setApplicationDestinationPrefixes("/app");
    }
    /**
     * 注册STOMP协议的节点，并指定映射的URL
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP协议节点，同时指定使用 SockJS 协议
        registry.addEndpoint("/chat").withSockJS();
    }

}
