package br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.controller;

import br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.entity.SolicitacaoReposicao;
import br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.service.SolicitacaoReposicaoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class SolicitacaoReposicaoController {

    private final SolicitacaoReposicaoService solicitacaoReposicaoService;

    public SolicitacaoReposicaoController(
            SolicitacaoReposicaoService solicitacaoReposicaoService) {

        this.solicitacaoReposicaoService = solicitacaoReposicaoService;
    }

    public SolicitacaoReposicao save(
            SolicitacaoReposicao solicitacaoReposicao) {

        return solicitacaoReposicaoService.save(
                solicitacaoReposicao
        );
    }

    public SolicitacaoReposicao update(
            Long id,
            SolicitacaoReposicao solicitacaoReposicao) {

        return solicitacaoReposicaoService.update(
                id,
                solicitacaoReposicao
        );
    }

    public List<SolicitacaoReposicao> findAll() {
        return solicitacaoReposicaoService.findAll();
    }

    public Optional<SolicitacaoReposicao> findById(Long id) {
        return solicitacaoReposicaoService.findById(id);
    }

    public void delete(Long id) {
        solicitacaoReposicaoService.delete(id);
    }
}