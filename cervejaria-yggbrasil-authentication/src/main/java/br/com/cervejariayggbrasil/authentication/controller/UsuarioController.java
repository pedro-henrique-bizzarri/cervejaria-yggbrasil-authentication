package br.com.cervejariayggbrasil.authentication.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cervejariayggbrasil.authentication.domain.dto.UsuarioRequest;
import br.com.cervejariayggbrasil.authentication.domain.dto.UsuarioResponse;
import br.com.cervejariayggbrasil.authentication.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest data){ 
        return ResponseEntity.ok(new UsuarioResponse((usuarioService.registrar(data.toUsuario()))));
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<UsuarioResponse> pesquisar(HttpServletRequest request){ 
        return ResponseEntity.ok(new UsuarioResponse((usuarioService.pesquisar(request))));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponse>> listar(){
        return ResponseEntity.ok(usuarioService.listar()
            .stream()
            .map(usuario -> new UsuarioResponse(usuario))
            .collect(Collectors.toList()));
    }

    @PutMapping("/liberar/{login}")
    public ResponseEntity<String> liberarAdmin(@PathVariable String login){
        usuarioService.liberarAdmin(login);
        return ResponseEntity.ok("Liberado permissão ADMIN para o usuário: ".concat(login));
    }
}
