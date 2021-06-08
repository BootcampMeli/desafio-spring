package br.com.mercadolivre.desafiospring.resources.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer userId) {
        super("User ID " + userId + " not found.");
    }
}
