package com.ivan.Order_Management_System.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserDataException extends RuntimeException {

    public InvalidUserDataException(String message) {
        super(message);
    }
}