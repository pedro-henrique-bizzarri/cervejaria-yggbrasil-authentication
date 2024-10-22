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
import java.util.ArrayList;

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
    public ResponseEntity<List<CadastroResponseDTO>> listar(){
        List<CadastroResponseDTO> lista = new ArrayList<CadastroResponseDTO>();
        authenticationService.listar().forEach(p -> lista.add(new CadastroResponseDTO(p)));
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/liberar-admin")
    public ResponseEntity<String> liberarAdmin(String login){
        if(authenticationService.liberarAdmin(login))
            return ResponseEntity.ok("Liberado permissão ADMIN para o usuário: ".concat(login));
        else
            return ResponseEntity.ok("Não foi possível liberar acesso ao usuário: ".concat(login));
    }
}
