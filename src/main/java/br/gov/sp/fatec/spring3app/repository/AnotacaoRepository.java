package br.gov.sp.fatec.spring3app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.spring3app.entity.Anotacao;

public interface AnotacaoRepository extends JpaRepository<Anotacao, Long>{
    
}
