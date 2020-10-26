package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.repository.ChatRoomRepository;
import com.reka.lakatos.angularchatbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

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

    public void saveNewMember(long roomId, String userName) {
        Optional<ChatRoom> roomById = chatRoomRepository.findById(roomId);
        AppUser userByUserName = userRepository.findUserByUserName(userName);
        if (roomById.isPresent()) {
            ChatRoom chatRoom = roomById.get();
            chatRoom.addNewMemberToRoom(userByUserName);
            chatRoomRepository.save(chatRoom);
        }
    }

    public ChatRoom getChatRoomById(long id) {
        return chatRoomRepository.getOne(id);
    }
}
