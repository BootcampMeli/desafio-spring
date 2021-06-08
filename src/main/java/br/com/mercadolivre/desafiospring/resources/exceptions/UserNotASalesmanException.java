package br.com.mercadolivre.desafiospring.resources.exceptions;

// TODO refatorar throws de exceptions
public class UserNotASalesmanException extends RuntimeException {

    public UserNotASalesmanException(Integer userId) {
        super("Id " + userId + " is not a Salesman");
    }
}
