package br.com.cervejariayggbrasil.authentication.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private HttpStatus httpStatus;

    private String message;

}
