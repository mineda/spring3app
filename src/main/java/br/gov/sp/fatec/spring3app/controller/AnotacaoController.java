package br.gov.sp.fatec.spring3app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.spring3app.entity.Anotacao;
import br.gov.sp.fatec.spring3app.service.IAnotacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anotacao")
public class AnotacaoController {

    @Autowired
    private IAnotacaoService service;

    @GetMapping
    public List<Anotacao> buscarTodasAnotacoes() {
        return service.buscarTodasAnotacoes();
    }

    @GetMapping(value = "/{id}")
    public Anotacao buscarAnotacaoPorId(@PathVariable("id") Long id) {
        return service.buscarAnotacao(id);
    }

    @PostMapping(value = "/novo/{texto}/{usuario}")
    public Anotacao novaAnotacao(@PathVariable("texto") String texto, @PathVariable("usuario") String usuario) {
        return service.novaAnotacao(texto, usuario);
    }

    @PostMapping(value = "/complemento/{texto}/{anotacao}")
    public Anotacao anotacaoComplementar(@PathVariable("texto") String texto, @PathVariable("anotacao") Long idAnotacao) {
        return service.anotacaoComplementar(idAnotacao, texto);
    }
    
}
