package br.com.cervejariayggbrasil.authentication.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Nenhum usuario encontrado !");
    }

    public UserNotFoundException(String msg){
        super(msg);
    }
}
