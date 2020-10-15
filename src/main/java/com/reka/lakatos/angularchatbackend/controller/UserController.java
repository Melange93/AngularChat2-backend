package com.reka.lakatos.angularchatbackend.controller;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/newuser")
    public ResponseEntity addNewUser(@RequestBody AppUser newUser) {
        try {
            userService.registerUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registration was successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

    @PostMapping("/getCreatedRooms")
    public ResponseEntity getCreatedRooms(@RequestBody AppUser oldUser) {
        try {
            List<ChatRoom> createdChatRoomByUserName = userService.getCreatedChatRoomByUserName(oldUser.getUserName());
            return ResponseEntity.status(HttpStatus.OK).body(createdChatRoomByUserName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }

    @PostMapping("/getMemberRooms")
    public ResponseEntity getMemberRooms(@RequestBody AppUser oldUser) {
        try {
            List<ChatRoom> memberChatRoomByUserName = userService.getMemberChatRoomByUserName(oldUser.getUserName());
            return ResponseEntity.status(HttpStatus.OK).body(memberChatRoomByUserName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
        }
    }
}
