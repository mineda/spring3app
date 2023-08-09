package br.gov.sp.fatec.spring3app.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.spring3app.entity.Autorizacao;
import br.gov.sp.fatec.spring3app.entity.Usuario;
import br.gov.sp.fatec.spring3app.repository.UsuarioRepository;

@Service
public class SegurancaService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOp = usuarioRepo.findByNome(username);
        if(usuarioOp.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        Usuario usuario = usuarioOp.get();
        return User.builder().username(username).password(usuario.getSenha())
            .authorities(usuario.getAutorizacoes().stream()
            .map(Autorizacao::getNome).collect(Collectors.toList())
            .toArray(new String[usuario.getAutorizacoes().size()]))
        .build();
    }
    
}
