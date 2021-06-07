package com.mercadolivre.socialmeli.exceptions;

public class NotAllowedToFollowException extends RuntimeException{

    public NotAllowedToFollowException(String msg){
        super(msg);
    }
}
