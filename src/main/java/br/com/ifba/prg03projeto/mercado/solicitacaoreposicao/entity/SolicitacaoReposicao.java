package br.com.ifba.prg03projeto.mercado.solicitacaoreposicao.entity;

import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;
import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoReposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataSolicitacao;

    private String status;

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