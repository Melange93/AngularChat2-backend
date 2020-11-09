package com.reka.lakatos.angularchatbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ChatRoomAlreadyExistException extends RuntimeException {
    public ChatRoomAlreadyExistException(String message) {
        super(message);
    }
}
