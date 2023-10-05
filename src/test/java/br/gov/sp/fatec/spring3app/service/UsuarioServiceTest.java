package br.gov.sp.fatec.spring3app.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.spring3app.entity.Usuario;
import br.gov.sp.fatec.spring3app.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private IUsuarioService service;

    @MockBean
    private UsuarioRepository usuarioRepo;

    @MockBean
    private PasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("Senha");
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario);
        Optional<Usuario> usuarioOp = Optional.of(usuario);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(usuarioOp);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);
        Mockito.when(usuarioRepo.findAll()).thenReturn(usuarios);
        Mockito.when(encoder.encode(any())).thenReturn("Senha");
    }

    @Test
    public void buscarUsuarioPorIdTestOk() {
        assertEquals("Teste", service.buscarPorId(1L).getNome());
    }

    @Test
    public void buscarUsuarioPorIdTestNOk() {
        assertThrows(ResponseStatusException.class, () -> {
            service.buscarPorId(2L);
        });
    }

    @Test
    public void novoUsuarioTestNOkNomeNull() {
        Usuario usuario = new Usuario(null, "Senha");
        assertThrows(ResponseStatusException.class, () -> {
                service.novoUsuario(usuario);
            });
    }

    @Test
    public void novoUsuarioTestOk() {
        Usuario usuario = new Usuario("Teste", "Senha");
        assertDoesNotThrow(() -> {
                service.novoUsuario(usuario);
            });
    }

    @Test
    public void todosUsuariosTestOk() {
        assertEquals(1, service.todosUsuarios().size());
    }
    
}
