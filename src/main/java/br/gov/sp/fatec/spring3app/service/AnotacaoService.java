package br.gov.sp.fatec.spring3app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.spring3app.entity.Anotacao;
import br.gov.sp.fatec.spring3app.entity.Usuario;
import br.gov.sp.fatec.spring3app.repository.AnotacaoRepository;
import br.gov.sp.fatec.spring3app.repository.UsuarioRepository;

@Service
public class AnotacaoService implements IAnotacaoService{

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private AnotacaoRepository anotacaoRepo;

    @Override
    public Anotacao novaAnotacao(String texto, String usuario) {
        if(texto == null || texto.isBlank() || usuario == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto ou usuário não informados!");
        }
        Optional<Usuario> usuarioOp = usuarioRepo.findByNome(usuario);
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        Anotacao anotacao = new Anotacao(texto, LocalDateTime.now(), usuarioOp.get());
        return anotacaoRepo.save(anotacao);
    }

    @Override
    public Anotacao buscarAnotacao(Long id) {
        Optional<Anotacao> anotacaoOp = anotacaoRepo.findById(id);
        if(anotacaoOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotação não encontrada!");
        }
        return anotacaoOp.get();
    }

    @Override
    public List<Anotacao> buscarTodasAnotacoes() {
        return anotacaoRepo.findAll();
    }

    @Override
    public Anotacao anotacaoComplementar(Long idAnotacao, String texto) {
        if(texto == null || texto.isBlank() || idAnotacao == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Texto ou anotação não informados!");
        }
        Optional<Anotacao> anotacaoOp = anotacaoRepo.findById(idAnotacao);
        if(anotacaoOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotação não encontrada!");
        }
        Anotacao anotacaoOriginal = anotacaoOp.get();
        texto = texto + " - Original: " + anotacaoOriginal.getTexto();
        Anotacao anotacao = new Anotacao(texto, LocalDateTime.now(), anotacaoOriginal.getUsuario());
        return anotacaoRepo.save(anotacao);
    }
    
}
