package br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.service;

import br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.entity.SolicitacaoReposicao;
import java.util.List;
import java.util.Optional;

public interface SolicitacaoReposicaoService {

    SolicitacaoReposicao save(
            SolicitacaoReposicao solicitacaoReposicao
    );

    SolicitacaoReposicao update(
            Long id,
            SolicitacaoReposicao solicitacaoReposicao
    );

    List<SolicitacaoReposicao> findAll();

    Optional<SolicitacaoReposicao> findById(Long id);

    void delete(Long id);
}