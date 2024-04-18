package com.example.websocket.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data // Getter & Setter 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 생성
public class HelloMessage {

    private String name;

}
