/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.service;

// Importa a entidade Produto.
import br.com.ifba.prg03projeto.mercado.entity.Produto;

// Importa o repositório responsável pelo acesso ao banco.
import br.com.ifba.prg03projeto.mercado.repository.ProdutoRepository;

// Importa List para retornar vários produtos.
import java.util.List;

// Importa Optional para tratar buscas que podem ou não encontrar um produto.
import java.util.Optional;

// Importa a anotação Service.
import org.springframework.stereotype.Service;


/**
 *
 * @author Davi
 */

@Service
public class ProdutoService {
    
    // Repositório usado para acessar a tabela produto.
    private final ProdutoRepository produtoRepository;

    // Construtor usado pelo Spring para injetar o repository.
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Salva um novo produto ou atualiza um produto existente.
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Retorna todos os produtos cadastrados.
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Busca um produto pelo ID.
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Remove um produto pelo ID.
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
    
}
