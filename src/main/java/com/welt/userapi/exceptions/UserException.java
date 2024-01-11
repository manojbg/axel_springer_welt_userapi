package com.welt.userapi.exceptions;


import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final ErrorType errorType;

    public UserException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

}
