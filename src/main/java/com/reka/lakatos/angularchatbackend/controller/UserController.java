package com.reka.lakatos.angularchatbackend.controller;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.model.UserResponse;
import com.reka.lakatos.angularchatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public UserResponse addNewUser(@RequestBody AppUser newUser) {
        AppUser savedUser = userService.registerUser(newUser);
        return UserResponse.builder()
                .userName(savedUser.getUserName())
                .build();
    }

    @PostMapping("/getcreatedrooms")
    public List<ChatRoom> getCreatedRooms(@RequestBody AppUser oldUser) {
        return userService.getCreatedChatRoomByUserName(oldUser.getUserName());
    }

    @PostMapping("/getmemberrooms")
    public List<ChatRoom> getMemberRooms(@RequestBody AppUser oldUser) {
        return userService.getMemberChatRoomByUserName(oldUser.getUserName());
    }
}
