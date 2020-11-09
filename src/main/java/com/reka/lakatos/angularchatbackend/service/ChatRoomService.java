package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.exception.ChatRoomNotFoundException;
import com.reka.lakatos.angularchatbackend.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public ChatRoom saveNewChatRoom(ChatRoom chatRoom) {
        String creatorName = chatRoom.getCreator().getUserName();
        AppUser userByUserName = userService.findUserByUserName(creatorName);
        chatRoom.setCreator(userByUserName);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom saveNewMember(long roomId, String userName) {
        ChatRoom chatRoomById = getChatRoomById(roomId);
        AppUser userByUserName = userService.findUserByUserName(userName);
        chatRoomById.addNewMemberToRoom(userByUserName);
        return chatRoomRepository.save(chatRoomById);
    }

    public ChatRoom getChatRoomById(long id) {
        Optional<ChatRoom> roomById = chatRoomRepository.findById(id);
        if (roomById.isPresent()) {
            return roomById.get();
        }
        throw new ChatRoomNotFoundException("Chat room not found.");
    }
}
