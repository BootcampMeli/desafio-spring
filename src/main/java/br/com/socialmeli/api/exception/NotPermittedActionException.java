package br.com.socialmeli.api.exception;

public class NotPermittedActionException extends RuntimeException{

    private static final long serialVersionUID = 3761685209651066701L;

    public NotPermittedActionException(String msg) {
        super(msg);
    }
}
