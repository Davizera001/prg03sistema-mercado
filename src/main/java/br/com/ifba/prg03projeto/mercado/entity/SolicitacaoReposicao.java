/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.entity;

// Importa as anotações de persistência do JPA.
import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// Importa a classe usada para armazenar data e horário.
import java.time.LocalDateTime;

// Importa as anotações do Lombok.
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoReposicao {

    // Identificador único da solicitação.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Data e horário em que a solicitação foi realizada.
    private LocalDateTime dataSolicitacao;

    // Situação atual da solicitação.
    private String status;

    // Quantidade solicitada para reposição.
    private Integer quantidadeSolicitada;

    /*
     * Muitas solicitações podem estar relacionadas ao mesmo produto.
     *
     * Não utilizamos cascade porque excluir uma solicitação
     * não pode excluir o produto.
     */
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    /*
     * Muitas solicitações podem ser realizadas pela mesma filial.
     *
     * Não utilizamos cascade porque a filial possui
     * existência independente da solicitação.
     */
    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;
}