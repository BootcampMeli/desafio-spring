package com.melidh.desafiospring.services.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String msg){
        super(msg);
    }

    public UserNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
