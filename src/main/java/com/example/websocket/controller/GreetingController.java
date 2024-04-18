package com.example.websocket.controller;

import com.example.websocket.domain.Greeting;
import com.example.websocket.domain.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello") // 전체경로 = "/app/hello"
    @SendTo("/topic/greetings") // "/app/hello"로 들어온 메시지를 함수에서 가공된 뒤 "/topic/greetings"로 발송
    public Greeting greeting(HelloMessage message) {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
