package br.com.cervejariayggbrasil.authentication.config;

import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cervejariayggbrasil.authentication.exception.IllegalUserAgeException;
import br.com.cervejariayggbrasil.authentication.exception.RestErrorMessage;
import br.com.cervejariayggbrasil.authentication.exception.UserNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        RestErrorMessage errorMessage = new RestErrorMessage();
        errorMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(exception.getBindingResult().getFieldErrors()
                                        .stream()
                                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                        .collect(Collectors.toList())
                                    .toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestErrorMessage> userNotFoundException(UserNotFoundException exception){
        RestErrorMessage errorMessage = new RestErrorMessage();
        errorMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalUserAgeException.class)
    public ResponseEntity<RestErrorMessage> illegalUserAgeException(IllegalUserAgeException exception){
        RestErrorMessage errorMessage = new RestErrorMessage();
        errorMessage.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
 }
