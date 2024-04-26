package com.example.websocket.controller;

import com.example.websocket.domain.Greeting;
import com.example.websocket.domain.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@RestController
public class GreetingController {

    @MessageMapping("/{roomId}") // 전체경로 = "/app/hello"
    @SendTo("/topic/{roomId}") // "/app/hello"로 들어온 메시지를 함수에서 가공된 뒤 "/topic/greetings"로 발송
    public String greeting(@DestinationVariable String roomId, @Payload HelloMessage message) {
        log.info(roomId);
        return "hello" + roomId;
    }
}
