package br.com.cervejariayggbrasil.authentication.exception;

public class UserExistsException extends RuntimeException{

    public UserExistsException(){
        super("Usuario existente no sistema !");
    }

    public UserExistsException(String msg){
        super(msg);
    }
}
