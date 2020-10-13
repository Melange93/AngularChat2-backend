package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.repository.ChatRoomRepository;
import com.reka.lakatos.angularchatbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoom saveNewChatRoom(ChatRoom chatRoom) {
        AppUser creator = chatRoom.getCreator();
        String userName = creator.getUserName();
        AppUser userByUserName = userRepository.findUserByUserName(userName);
        chatRoom.setCreator(userByUserName);
        return chatRoomRepository.save(chatRoom);
    }
}
