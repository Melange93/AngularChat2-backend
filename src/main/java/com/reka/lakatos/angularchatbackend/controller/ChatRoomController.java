package com.reka.lakatos.angularchatbackend.controller;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.model.RoomMemberCredentials;
import com.reka.lakatos.angularchatbackend.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/addchatroom")
    public ResponseEntity saveNewRoom(@RequestBody ChatRoom chatRoom) {
        try {
            ChatRoom savedRoom = chatRoomService.saveNewChatRoom(chatRoom);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

    @PostMapping("/addnewmember")
    public ResponseEntity addNewMemberToChatRoom(@RequestBody RoomMemberCredentials roomMemberCredentials) {
        try {
            long chatRoomId = roomMemberCredentials.getChatRoomId();
            String userName = roomMemberCredentials.getUserName();
            chatRoomService.saveNewMember(chatRoomId, userName);
            return ResponseEntity.status(HttpStatus.OK).body("User registration was successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

}
