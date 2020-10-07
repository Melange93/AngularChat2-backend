package com.reka.lakatos.angularchatbackend.controller;

import com.reka.lakatos.angularchatbackend.entity.ChatMessage;
import com.reka.lakatos.angularchatbackend.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatService;

    @PostMapping("/insert-message")
    public ChatMessage insertMessage(@RequestBody ChatMessage chatMessage) {
        return chatService.saveChatMessage(chatMessage);
    }
}
