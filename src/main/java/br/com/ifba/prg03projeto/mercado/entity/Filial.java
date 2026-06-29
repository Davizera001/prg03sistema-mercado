/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.entity;

// Importa as anotações de persistência do JPA.
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Importa a anotação que controla a serialização do lado principal.
import com.fasterxml.jackson.annotation.JsonManagedReference;

// Importa as coleções utilizadas pela entidade.
import java.util.ArrayList;
import java.util.List;

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
public class Filial {

    // Identificador único da filial.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da filial.
    private String nome;

    // Endereço da filial.
    private String endereco;

    /*
     * Uma filial pode possuir vários registros de estoque.
     *
     * CascadeType.ALL:
     * as operações realizadas na filial também podem ser aplicadas
     * aos registros de estoque pertencentes a ela.
     *
     * orphanRemoval:
     * remove do banco um estoque retirado da lista da filial.
     */
    @OneToMany(
        mappedBy = "filial",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<EstoqueFilial> estoques = new ArrayList<>();
}
