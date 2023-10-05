package br.gov.sp.fatec.spring3app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.gov.sp.fatec.spring3app.entity.Usuario;
import br.gov.sp.fatec.spring3app.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService service;

    @Test
    //Utilizado para simular um usuário autenticado com a role ADMIN
    @WithMockUser(roles = {"ADMIN"})
    public void novoUsuarioTestOk() throws Exception {
        Usuario usuario = new Usuario("TesteMvc", "senha");
        usuario.setId(1L);
        Mockito.when(service.novoUsuario(any())).thenReturn(usuario);

        mvc.perform(post("/usuario")
            .content("{\"nome\":\"TesteMvc\", \"senha\":\"senha\"}")
            .contentType(MediaType.APPLICATION_JSON)
            // Para o @WebMvcTest, o Spring Security não carrega o arquivo de configuração personalizado. 
            // Por conta disso, é necessário enviar um csrf em cada POST (padrão do Spring Security)
            .with(csrf()))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("TesteMvc"))
            .andExpect(jsonPath("$.senha").value("senha"));
    }

    @Test
    @WithMockUser
    public void todosUsuariosTestOk() throws Exception {
        Usuario usuario = new Usuario("TesteMvc", "senha");
        usuario.setId(1L);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario);
        Mockito.when(service.todosUsuarios()).thenReturn(usuarios);

        mvc.perform(get("/usuario"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].nome").value("TesteMvc"))
            .andExpect(jsonPath("$[0].senha").value("senha"));
    }
    
}
