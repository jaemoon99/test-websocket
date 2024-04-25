package com.example.websocket.controller;

import com.example.websocket.domain.v2.Chat;
import com.example.websocket.domain.v2.ChatType;
import com.example.websocket.dto.v2.EnterAndExitRequestDto;
import com.example.websocket.dto.v2.SendMessageRequestDto;
import com.example.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatControllerV2 {

    private final ChatService chatService;

    // 채팅방 입장
    @MessageMapping("enter.v2.{roomId}")
    public Chat enter(
            @DestinationVariable(value = "roomId") String roomId,
            @Payload EnterAndExitRequestDto request) {
        return chatService.enterRoom(roomId, request);
    }

    // 채팅방 대화
    @MessageMapping("talk.v2.{roomId}")
    public Chat talk(
            @DestinationVariable(value = "roomId") String roomId,
            @Payload SendMessageRequestDto request) {
        return chatService.sendMessage(roomId, request);
    }

    // 채팅방 퇴장
    @MessageMapping("exit.v2.{roomId}")
    public Chat exit(
            @DestinationVariable(value = "roomId") String roomId,
            @Payload EnterAndExitRequestDto request) {
        return chatService.exitRoom(roomId, request);
    }

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/chat")
    public void test() {
        Chat chat = Chat.builder()
                .type(ChatType.EXIT)
                .roomId("1")
                .userName("sender2")
                .content("sender2 님이 퇴장했습니다.")
                .build();
        rabbitTemplate.convertAndSend("chat.exchange", "room." + 1, chat); // 도착지 = chat.exchange/room.{roomId}
    }
}
