/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.controller;

// Importa a entidade Solicitação de Reposição.
import br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao;

// Importa o serviço da entidade.
import br.com.ifba.prg03projeto.mercado.service.SolicitacaoReposicaoService;

// Importa as coleções utilizadas.
import java.util.List;

// Importa as classes HTTP do Spring.
import org.springframework.http.ResponseEntity;

// Importa as anotações REST do Spring.
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitacoes-reposicao")
public class SolicitacaoReposicaoController {

    // Serviço utilizado para acessar as operações da entidade.
    private final SolicitacaoReposicaoService solicitacaoReposicaoService;

    // Construtor utilizado pelo Spring para injetar o serviço.
    public SolicitacaoReposicaoController(
            SolicitacaoReposicaoService solicitacaoReposicaoService) {

        this.solicitacaoReposicaoService = solicitacaoReposicaoService;
    }

    // Cadastra uma nova solicitação.
    @PostMapping
    public SolicitacaoReposicao salvar(
            @RequestBody SolicitacaoReposicao solicitacaoReposicao) {

        return solicitacaoReposicaoService.salvar(solicitacaoReposicao);
    }

    // Lista todas as solicitações.
    @GetMapping
    public List<SolicitacaoReposicao> listarTodos() {
        return solicitacaoReposicaoService.listarTodos();
    }

    // Busca uma solicitação pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoReposicao> buscarPorId(
            @PathVariable Long id) {

        return solicitacaoReposicaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza uma solicitação existente.
    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoReposicao> atualizar(
            @PathVariable Long id,
            @RequestBody SolicitacaoReposicao solicitacaoAtualizada) {

        return solicitacaoReposicaoService.buscarPorId(id)
                .map(solicitacaoExistente -> {

                    // Atualiza os dados da solicitação encontrada.
                    solicitacaoExistente.setDataSolicitacao(
                            solicitacaoAtualizada.getDataSolicitacao()
                    );

                    solicitacaoExistente.setStatus(
                            solicitacaoAtualizada.getStatus()
                    );

                    solicitacaoExistente.setQuantidadeSolicitada(
                            solicitacaoAtualizada.getQuantidadeSolicitada()
                    );

                    solicitacaoExistente.setProduto(
                            solicitacaoAtualizada.getProduto()
                    );

                    solicitacaoExistente.setFilial(
                            solicitacaoAtualizada.getFilial()
                    );

                    // Salva e retorna a solicitação atualizada.
                    return ResponseEntity.ok(
                            solicitacaoReposicaoService.salvar(
                                    solicitacaoExistente
                            )
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Remove uma solicitação pelo ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        // Verifica se a solicitação existe antes de remover.
        if (solicitacaoReposicaoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        solicitacaoReposicaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}