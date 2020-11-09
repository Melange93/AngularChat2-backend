package com.reka.lakatos.angularchatbackend.service;

import com.reka.lakatos.angularchatbackend.entity.AppUser;
import com.reka.lakatos.angularchatbackend.exception.EmailAlreadyExistException;
import com.reka.lakatos.angularchatbackend.exception.InvalidEmailException;
import com.reka.lakatos.angularchatbackend.exception.InvalidPasswordException;
import com.reka.lakatos.angularchatbackend.exception.UserNameAlreadyExistException;
import com.reka.lakatos.angularchatbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserInputChecker {

    private final UserRepository userRepository;

    public void checkUserInputNewUser(AppUser user) {
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidEmailException("Email address is incorrect.");
        }

        if (isEmailAlreadyExist(user.getEmail())) {
            throw new EmailAlreadyExistException("Email already exist.");
        }

        if (!isValidUserName(user.getUserName())) {
            throw new UserNameAlreadyExistException("User name already exist.");
        }

        if (!isValidPassword(user.getPassword())) {
            throw new InvalidPasswordException("Invalid password.");
        }
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = Pattern.compile("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b").matcher(email);
        return matcher.find();
    }

    private boolean isEmailAlreadyExist(String email) {
        return userRepository.findAppUserByEmail(email).isPresent();
    }

    private boolean isValidUserName(String userName) {
        return userRepository.findAppUserByUserName(userName).isEmpty();
    }

    private boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$").matcher(password);
        return matcher.find();
    }
}
