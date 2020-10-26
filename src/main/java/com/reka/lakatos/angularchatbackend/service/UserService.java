package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void registerUser(AppUser user) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public AppUser findUserByUserName(String name) {
        return userRepository.findUserByUserName(name);
    }

    public String getEmailByUserName(String name) {
       return findUserByUserName(name).getEmail();
    }

    public List<ChatRoom> getCreatedChatRoomByUserName(String name) {
        return findUserByUserName(name).getCreatedRooms();
    }

    public List<ChatRoom> getMemberChatRoomByUserName(String name) {
        return findUserByUserName(name).getMember();
    }
}
