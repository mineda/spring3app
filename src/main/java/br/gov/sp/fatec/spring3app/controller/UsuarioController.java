package br.gov.sp.fatec.spring3app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.spring3app.entity.Usuario;
import br.gov.sp.fatec.spring3app.service.IUsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Usuario novoUsuario(@RequestBody Usuario usuario) {
        return service.novoUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> todosUsuarios() {
        return service.todosUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }
    
}
