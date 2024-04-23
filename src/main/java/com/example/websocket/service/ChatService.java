package com.example.websocket.service;

import com.example.websocket.domain.v2.Chat;
import com.example.websocket.domain.v2.ChatType;
import com.example.websocket.dto.v2.EnterAndExitRequestDto;
import com.example.websocket.dto.v2.SendMessageRequestDto;
import com.example.websocket.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final RabbitTemplate rabbitTemplate;
    private final ChatRepository chatRepository;

    public Chat enterRoom(String roomId, EnterAndExitRequestDto request) {
        Chat chat = Chat.builder()
                .type(ChatType.ENTER)
                .roomId(roomId)
                .userName(request.getUserName())
                .content(request.getUserName() + "님이 입장했습니다.")
                .build();

        // 구독 URL = /exchange/chat.exchange/room.{roomId}
        // 전송 URL = /app/enter.v2.{roomId}
        rabbitTemplate.convertAndSend("chat.exchange", "room." + roomId, chat); // 도착지 = chat.exchange/room.{roomId}
        log.info("Enter RoomId = {}", roomId);

        return chatRepository.save(chat);
    }

    public Chat sendMessage(String roomId, SendMessageRequestDto request) {
        Chat chat = Chat.builder()
                .type(ChatType.TALK)
                .roomId(roomId)
                .userName(request.getUserName())
                .content(request.getContent())
                .build();

        // 구독 URL = /exchange/chat.exchange/room.{roomId}
        // 전송 URL = /app/talk.v2.{roomId}
        rabbitTemplate.convertAndSend("chat.exchange", "room." + roomId, chat); // 도착지 = chat.exchange/room.{roomId}
        log.info("Chat RoomId = {}", roomId);

        return chatRepository.save(chat);
    }

    public Chat exitRoom(String roomId, EnterAndExitRequestDto request) {
        Chat chat = Chat.builder()
                .type(ChatType.EXIT)
                .roomId(roomId)
                .userName(request.getUserName())
                .content(request.getUserName() + "님이 퇴장했습니다.")
                .build();

        // 구독 URL = /exchange/chat.exchange/room.{roomId}
        // 전송 URL = /app/exit.v2.{roomId}
        rabbitTemplate.convertAndSend("chat.exchange", "room." + roomId, chat); // 도착지 = chat.exchange/room.{roomId}
        log.info("Exit RoomId = {}", roomId);

        return chatRepository.save(chat);
    }
}
