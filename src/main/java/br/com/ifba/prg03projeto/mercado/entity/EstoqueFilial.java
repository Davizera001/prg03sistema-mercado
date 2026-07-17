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

// Importa a anotação que impede a repetição infinita no JSON.
import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class EstoqueFilial {

    // Identificador único do registro de estoque.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quantidade disponível do produto na filial.
    private Integer quantidade;

    /*
     * Muitos registros de estoque podem pertencer à mesma filial.
     *
     * Não colocamos cascade aqui porque a filial possui
     * existência própria.
     */
    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    @JsonBackReference
    private Filial filial;

    /*
     * Muitos registros de estoque podem apontar para o mesmo produto.
     *
     * Não usamos CascadeType.REMOVE ou CascadeType.ALL,
     * pois excluir um estoque não deve excluir o produto.
     */
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
}
