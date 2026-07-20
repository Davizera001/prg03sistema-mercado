package br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.service;

import br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.entity.SolicitacaoReposicao;
import br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.repository.SolicitacaoReposicaoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoReposicaoServiceImpl
        implements SolicitacaoReposicaoService {

    private final SolicitacaoReposicaoRepository solicitacaoReposicaoRepository;

    public SolicitacaoReposicaoServiceImpl(
            SolicitacaoReposicaoRepository solicitacaoReposicaoRepository) {

        this.solicitacaoReposicaoRepository = solicitacaoReposicaoRepository;
    }

    @Override
    public SolicitacaoReposicao save(
            SolicitacaoReposicao solicitacaoReposicao) {

        if (solicitacaoReposicao.getDataSolicitacao() == null) {
            solicitacaoReposicao.setDataSolicitacao(
                    LocalDateTime.now()
            );
        }

        if (solicitacaoReposicao.getStatus() == null
                || solicitacaoReposicao.getStatus().isBlank()) {

            solicitacaoReposicao.setStatus("PENDENTE");
        }

        validate(solicitacaoReposicao);

        return solicitacaoReposicaoRepository.save(
                solicitacaoReposicao
        );
    }

    @Override
    public SolicitacaoReposicao update(
            Long id,
            SolicitacaoReposicao solicitacaoReposicao) {

        SolicitacaoReposicao solicitacaoExistente =
                solicitacaoReposicaoRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Solicitação de reposição não encontrada."
                                )
                        );

        validate(solicitacaoReposicao);

        solicitacaoExistente.setQuantidadeSolicitada(
                solicitacaoReposicao.getQuantidadeSolicitada()
        );

        solicitacaoExistente.setProduto(
                solicitacaoReposicao.getProduto()
        );

        solicitacaoExistente.setFilial(
                solicitacaoReposicao.getFilial()
        );

        if (solicitacaoReposicao.getStatus() != null
                && !solicitacaoReposicao.getStatus().isBlank()) {

            solicitacaoExistente.setStatus(
                    solicitacaoReposicao.getStatus().toUpperCase()
            );
        }

        if (solicitacaoReposicao.getDataSolicitacao() != null) {
            solicitacaoExistente.setDataSolicitacao(
                    solicitacaoReposicao.getDataSolicitacao()
            );
        }

        return solicitacaoReposicaoRepository.save(
                solicitacaoExistente
        );
    }

    @Override
    public List<SolicitacaoReposicao> findAll() {
        return solicitacaoReposicaoRepository.findAll();
    }

    @Override
    public Optional<SolicitacaoReposicao> findById(Long id) {
        return solicitacaoReposicaoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {

        if (!solicitacaoReposicaoRepository.existsById(id)) {
            throw new RuntimeException(
                    "Solicitação de reposição não encontrada."
            );
        }

        solicitacaoReposicaoRepository.deleteById(id);
    }

    private void validate(
            SolicitacaoReposicao solicitacaoReposicao) {

        if (solicitacaoReposicao.getProduto() == null) {
            throw new RuntimeException(
                    "O produto é obrigatório."
            );
        }

        if (solicitacaoReposicao.getFilial() == null) {
            throw new RuntimeException(
                    "A filial é obrigatória."
            );
        }

        if (solicitacaoReposicao.getQuantidadeSolicitada() == null) {
            throw new RuntimeException(
                    "A quantidade solicitada é obrigatória."
            );
        }

        if (solicitacaoReposicao.getQuantidadeSolicitada() <= 0) {
            throw new RuntimeException(
                    "A quantidade solicitada deve ser maior que zero."
            );
        }
    }
}