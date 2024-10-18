package br.com.cervejariayggbrasil.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class CervejariaYggbrasilController {

    @GetMapping
    public String getMessage(){
        return "Seja bem vindo a Cervejaria Yggbrasil !";
    }

}
