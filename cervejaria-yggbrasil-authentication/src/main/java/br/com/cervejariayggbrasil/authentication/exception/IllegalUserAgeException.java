package br.com.cervejariayggbrasil.authentication.exception;

public class IllegalUserAgeException extends RuntimeException{

    public IllegalUserAgeException(){
        super("O usuario e menor de idade !");
    }

    public IllegalUserAgeException(String msg){
        super(msg);
    }
}
