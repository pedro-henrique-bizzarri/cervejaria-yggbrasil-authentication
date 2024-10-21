package br.com.cervejariayggbrasil.authentication.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cervejariayggbrasil.authentication.domain.dto.CervejaDTO;
import java.util.List;

@RestController
@RequestMapping("cerveja")
public class CervejaController {

    @PostMapping("/cadastrar")
    public RequestEntity<CervejaDTO> cadastrarCerveja(CervejaDTO cerveja){
        // TODO 
        return null;
    }

    @GetMapping("/listar-cervejas")
    public RequestEntity<List<CervejaDTO>> listarCervejas(){
        // TODO
        return null;
    }
}
