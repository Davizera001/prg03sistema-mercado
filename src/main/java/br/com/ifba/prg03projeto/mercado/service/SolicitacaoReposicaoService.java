/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.service;

// Importa a entidade Solicitação de Reposição.
import br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao;

// Importa o repositório da entidade.
import br.com.ifba.prg03projeto.mercado.repository.SolicitacaoReposicaoRepository;

// Importa as coleções utilizadas.
import java.util.List;
import java.util.Optional;

// Importa a anotação de serviço do Spring.
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoReposicaoService {

    // Repositório usado para acessar o banco de dados.
    private final SolicitacaoReposicaoRepository solicitacaoReposicaoRepository;

    // Construtor utilizado pelo Spring para injetar o repositório.
    public SolicitacaoReposicaoService(
            SolicitacaoReposicaoRepository solicitacaoReposicaoRepository) {

        this.solicitacaoReposicaoRepository = solicitacaoReposicaoRepository;
    }

    // Salva ou atualiza uma solicitação.
    public SolicitacaoReposicao salvar(
            SolicitacaoReposicao solicitacaoReposicao) {

        return solicitacaoReposicaoRepository.save(solicitacaoReposicao);
    }

    // Lista todas as solicitações.
    public List<SolicitacaoReposicao> listarTodos() {
        return solicitacaoReposicaoRepository.findAll();
    }

    // Busca uma solicitação pelo ID.
    public Optional<SolicitacaoReposicao> buscarPorId(Long id) {
        return solicitacaoReposicaoRepository.findById(id);
    }

    // Remove uma solicitação pelo ID.
    public void deletar(Long id) {
        solicitacaoReposicaoRepository.deleteById(id);
    }
}