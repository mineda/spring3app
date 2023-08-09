package br.gov.sp.fatec.spring3app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.spring3app.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    public Optional<Usuario> findByNome(String nome);
    
}
