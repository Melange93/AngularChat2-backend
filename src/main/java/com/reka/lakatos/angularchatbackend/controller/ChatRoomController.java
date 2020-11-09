package com.reka.lakatos.angularchatbackend.controller;

import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.model.NewRoomMemberResponse;
import com.reka.lakatos.angularchatbackend.model.RoomMemberCredentials;
import com.reka.lakatos.angularchatbackend.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/addchatroom")
    public ChatRoom saveNewRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.saveNewChatRoom(chatRoom);
    }

    @PostMapping("/addnewmember")
    public NewRoomMemberResponse addNewMemberToChatRoom(@RequestBody RoomMemberCredentials roomMemberCredentials) {
        long chatRoomId = roomMemberCredentials.getChatRoomId();
        String userName = roomMemberCredentials.getUserName();
        ChatRoom chatRoom = chatRoomService.saveNewMember(chatRoomId, userName);
        return NewRoomMemberResponse.builder()
                .newMemberName(userName)
                .chatRoomName(chatRoom.getChatRoomName())
                .build();
    }

}
