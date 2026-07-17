/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.produto.repository;


// Importa List para retornar uma lista de produtos.
import java.util.List;

// importando a entidade Produto.
import br.com.ifba.prg03projeto.mercado.produto.entity.Produto;

// Importa a interface JpaRepository,
// responsável pelas operações de persistência.
import org.springframework.data.jpa.repository.JpaRepository;

// Importa a anotação Repository,
// utilizada para identificar a camada de acesso a dados.
import org.springframework.stereotype.Repository;

/**
 *
 * @author Davi
 */

/*
 * Interface responsável pela comunicação
 * entre a aplicação e a tabela Produto.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    
    // Busca produtos pelo nome, ignorando letras maiúsculas e minúsculas.
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    /*
     * O JpaRepository disponibiliza automaticamente
     * diversos métodos para manipulação de dados:
     *
     * save()
     * findAll()
     * findById()
     * deleteById()
     * existsById()
     *
     * Não é necessário implementá-los manualmente.
     */

}
