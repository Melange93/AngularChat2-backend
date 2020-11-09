package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.entity.ChatRoom;
import com.reka.lakatos.angularchatbackend.exception.AppUserNotFoundException;
import com.reka.lakatos.angularchatbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserInputChecker userInputChecker;

    public AppUser registerUser(AppUser user) {
        userInputChecker.checkUserInputNewUser(user);
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser findUserByUserName(String name) {
        Optional<AppUser> appUserByUserName = userRepository.findAppUserByUserName(name);
        if (appUserByUserName.isPresent()) {
            return appUserByUserName.get();
        }
        throw new AppUserNotFoundException("User not found.");
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
