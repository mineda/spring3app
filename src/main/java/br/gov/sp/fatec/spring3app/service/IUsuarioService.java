package br.gov.sp.fatec.spring3app.service;

import java.util.List;

import br.gov.sp.fatec.spring3app.entity.Usuario;

public interface IUsuarioService {

    public Usuario novoUsuario(Usuario usuario);

    public List<Usuario> todosUsuarios();

    public Usuario buscarPorId(Long id);
    
}
