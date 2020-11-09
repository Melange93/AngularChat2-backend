package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatMessage;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final UserService userService;

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        setAppUserToChatMessage(chatMessage);
        setChatRoomToChatMessage(chatMessage);
        return chatMessageRepository.save(chatMessage);
    }

    private void setChatRoomToChatMessage(ChatMessage chatMessage) {
        long chatRoomId = chatMessage.getChatRoom().getId();
        ChatRoom chatRoomById = chatRoomService.getChatRoomById(chatRoomId);
        chatMessage.setChatRoom(chatRoomById);
    }

    private void setAppUserToChatMessage(ChatMessage chatMessage) {
        String userName = chatMessage.getUser().getUserName();
        AppUser userFromDb = userService.findUserByUserName(userName);
        chatMessage.setUser(userFromDb);
    }

}
