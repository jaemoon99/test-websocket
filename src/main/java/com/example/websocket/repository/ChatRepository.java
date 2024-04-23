package com.example.websocket.repository;

import com.example.websocket.domain.v2.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, Long> {
}
