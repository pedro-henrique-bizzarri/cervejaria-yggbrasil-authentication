package br.com.cervejariayggbrasil.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cervejariayggbrasil.authentication.domain.dto.AuthenticationDTO;
import br.com.cervejariayggbrasil.authentication.domain.dto.CadastroDTO;
import br.com.cervejariayggbrasil.authentication.domain.dto.CadastroResponseDTO;
import br.com.cervejariayggbrasil.authentication.domain.dto.LoginResponseDTO;
import br.com.cervejariayggbrasil.authentication.service.AuthenticationService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody @Valid AuthenticationDTO data){
        return ResponseEntity.ok(new LoginResponseDTO(authenticationService.authenticate(data)));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroResponseDTO> cadastrar(@RequestBody @Valid CadastroDTO data){ 
        return ResponseEntity.ok(new CadastroResponseDTO((authenticationService.cadastrar(data))));
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<CadastroDTO>> listar(){
        // TODO
        return null;
    }

    @PutMapping("/liberar-admin")
    public ResponseEntity<LoginResponseDTO> liberarAdmin(String login){
        return null;
    }
}
