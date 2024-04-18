package com.example.websocket.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter & Setter 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
public class RequestMessage {

    private String sender;
    private String receiver;
    private String content;

}
