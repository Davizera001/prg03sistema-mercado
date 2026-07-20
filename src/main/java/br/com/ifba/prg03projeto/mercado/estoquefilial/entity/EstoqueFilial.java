/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.estoquefilial.entity;

import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;
import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueFilial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quantidade disponível do produto na filial.
    private Integer quantidade;

    /*
     * Muitos registros de estoque pertencem a uma filial.
     *
     * Não utilizamos cascade porque a filial possui
     * ciclo de vida próprio.
     */
    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    @JsonBackReference
    private Filial filial;

    /*
     * Muitos registros de estoque podem referenciar
     * o mesmo produto.
     *
     * O produto também possui existência própria,
     * portanto não utilizamos CascadeType.ALL.
     */
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
}
