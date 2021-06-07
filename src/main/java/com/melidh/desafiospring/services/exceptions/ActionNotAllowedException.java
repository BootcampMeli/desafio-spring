package com.melidh.desafiospring.services.exceptions;

public class ActionNotAllowedException extends RuntimeException{

    public ActionNotAllowedException(String msg){
        super(msg);
    }

    public ActionNotAllowedException(String msg, Throwable cause){
        super(msg, cause);
    }
}
