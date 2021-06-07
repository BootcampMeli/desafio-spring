package com.melidh.desafiospring.resources.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.melidh.desafiospring.services.exceptions.ActionNotAllowedException;
import com.melidh.desafiospring.services.exceptions.DateParseException;
import com.melidh.desafiospring.services.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(UserNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({
            ActionNotAllowedException.class,
            DateParseException.class
    })
    public ResponseEntity<StandardError> actionNotAllowed(Exception e, HttpServletRequest request){
        StandardError error = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(
            MethodArgumentNotValidException e,
            HttpServletRequest request){

        ValidationError validationError = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validacao",
                System.currentTimeMillis());

        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            validationError.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<StandardError> pathVariableTypeMismatch(
            NumberFormatException e,
            HttpServletRequest request
    ){
        StandardError error = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Id deve ser um inteiro",
                System.currentTimeMillis()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> validation(
            InvalidFormatException e,
            HttpServletRequest request){

        StandardError error = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Formato de input incorreto: "+ e.getValue(),
                System.currentTimeMillis()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
