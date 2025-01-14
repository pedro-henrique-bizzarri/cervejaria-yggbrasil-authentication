package br.com.cervejariayggbrasil.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cervejariayggbrasil.authentication.domain.dto.AuthenticationRequest;
import br.com.cervejariayggbrasil.authentication.domain.dto.LoginResponse;
import br.com.cervejariayggbrasil.authentication.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid AuthenticationRequest data){
        return ResponseEntity.ok(new LoginResponse(authenticationService.authenticate(data.toUser())));
    }

}
