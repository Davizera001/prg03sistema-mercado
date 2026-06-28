/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.repository;

// Importa a entidade Solicitação de Reposição.
import br.com.ifba.prg03projeto.mercado.entity.SolicitacaoReposicao;

// Importa o JpaRepository do Spring Data.
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoReposicaoRepository
        extends JpaRepository<SolicitacaoReposicao, Long> {
}
