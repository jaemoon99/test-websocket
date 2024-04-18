package com.example.websocket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseMessage {

    private String roomId;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public ResponseMessage(String roomId, String sender, String receiver, String content) {
        this.roomId = roomId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
