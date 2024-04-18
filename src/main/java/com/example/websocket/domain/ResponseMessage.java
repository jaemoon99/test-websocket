package com.example.websocket.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // Getter & Setter 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
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
