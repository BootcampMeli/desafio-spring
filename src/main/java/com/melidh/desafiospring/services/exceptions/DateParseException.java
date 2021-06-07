package com.melidh.desafiospring.services.exceptions;

public class DateParseException extends RuntimeException{

    public DateParseException(String msg){
        super(msg);
    }

    public DateParseException(String msg, Throwable cause){
        super(msg, cause);
    }
}
