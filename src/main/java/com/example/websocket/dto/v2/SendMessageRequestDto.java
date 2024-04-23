package com.example.websocket.dto.v2;

import com.example.websocket.domain.v2.ChatType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendMessageRequestDto {
    private ChatType type;
    private String userName;
    private String content;
}
