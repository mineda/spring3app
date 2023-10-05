package br.gov.sp.fatec.spring3app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.spring3app.entity.Autorizacao;
import br.gov.sp.fatec.spring3app.entity.Usuario;
import br.gov.sp.fatec.spring3app.repository.AutorizacaoRepository;
import br.gov.sp.fatec.spring3app.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @Autowired
    private PasswordEncoder encoder;

    //@PreAuthorize("hasRole('ADMIN')")
    public Usuario novoUsuario(Usuario usuario) {
        if(usuario == null ||
                usuario.getAutorizacoes() == null ||
                usuario.getNome() == null ||
                usuario.getNome().isBlank() ||
                usuario.getSenha() == null ||
                usuario.getSenha().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos para usuario!");
        }
        List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
        for(Autorizacao aut: usuario.getAutorizacoes()) {
            Autorizacao nova;
            if(aut.getId() == null) {
                nova = novaAutorizacao(aut);
            }
            else {
                Optional<Autorizacao> autOp = autorizacaoRepo.findById(aut.getId());
                if(autOp.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autorizacao com id " + aut.getId() + " nao encontrada!");
                }
                nova = autOp.get();
            }
            autorizacoes.add(nova);
        }
        usuario.setAutorizacoes(autorizacoes);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepo.save(usuario);
    }

    private Autorizacao novaAutorizacao(Autorizacao aut) {
        if(aut.getNome() == null || aut.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos para autorizacao!");
        }
        return autorizacaoRepo.save(aut);
    }

    //@PreAuthorize("isAuthenticated")
    public List<Usuario> todosUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }
        return usuarioOp.get();
    }
    
}
