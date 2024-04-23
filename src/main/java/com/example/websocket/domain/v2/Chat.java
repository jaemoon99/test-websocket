package com.example.websocket.domain.v2;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "chat")
public class Chat {

    @Id
    private String id;
    private ChatType type;
    private String roomId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public Chat(ChatType type, String roomId, String userName, String content) {
        this.type = type;
        this.roomId = roomId;
        this.userName = userName;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
