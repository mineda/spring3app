package br.gov.sp.fatec.spring3app.service;

import java.util.List;

import br.gov.sp.fatec.spring3app.entity.Anotacao;

public interface IAnotacaoService {

    public Anotacao novaAnotacao(String texto, String usuario);

    public Anotacao buscarAnotacao(Long id);

    public List<Anotacao> buscarTodasAnotacoes();

    public Anotacao anotacaoComplementar(Long idAnotacao, String texto);
    
}
