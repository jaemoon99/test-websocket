package com.example.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.setPathMatcher(new AntPathMatcher(".")); // url의 "/"를 "."으로 변경
        registry.setApplicationDestinationPrefixes("/app"); // 구독자들에게 전달 url
//        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue"); // 구독 url
        registry.enableSimpleBroker("/topic"); // 바로 구독자들에게 전달
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket") // WebSocket에 접속할 EndPoint 설정
                .setAllowedOrigins("*"); // WebSocket 연결에 대한 CORS설정, 모든 오리진에서의 요청을 허용
    }
}
