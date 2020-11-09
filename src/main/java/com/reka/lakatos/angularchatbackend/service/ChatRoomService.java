package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.exception.ChatRoomAlreadyExistException;
import com.reka.lakatos.angularchatbackend.exception.ChatRoomNotFoundException;
import com.reka.lakatos.angularchatbackend.exception.UserNameAlreadyExistException;
import com.reka.lakatos.angularchatbackend.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public ChatRoom saveNewChatRoom(ChatRoom chatRoom) {
        String creatorName = chatRoom.getCreator().getUserName();
        AppUser userByUserName = userService.findUserByUserName(creatorName);

        if (haveUserChatRoomByName(chatRoom.getChatRoomName(), userByUserName.getCreatedRooms())) {
            throw new ChatRoomAlreadyExistException("Chat room this already exist with this name.");
        }

        chatRoom.setCreator(userByUserName);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom saveNewMember(long roomId, String userName) {
        ChatRoom chatRoomById = getChatRoomById(roomId);

        if (isUserMemberThisRoom(userName, chatRoomById.getMembers())) {
            throw new UserNameAlreadyExistException("User already member this chat room");
        }

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

    private boolean haveUserChatRoomByName(String name, List<ChatRoom> userChatRooms) {
        long count = userChatRooms.stream()
                .filter(chatRoom -> chatRoom.getChatRoomName().equals(name))
                .count();
        return count > 0;
    }

    private boolean isUserMemberThisRoom(String newUserName, Set<AppUser> members) {
        long count = members.stream()
                .filter(appUser -> appUser.getUserName().equals(newUserName))
                .count();
        return count > 0;
    }
}
