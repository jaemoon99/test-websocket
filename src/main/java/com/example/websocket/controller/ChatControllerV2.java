package com.example.websocket.controller;

import com.example.websocket.domain.v2.Chat;
import com.example.websocket.dto.v2.EnterAndExitRequestDto;
import com.example.websocket.dto.v2.SendMessageRequestDto;
import com.example.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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
}
