package br.gov.sp.fatec.spring3app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.spring3app.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long>{
    
}
