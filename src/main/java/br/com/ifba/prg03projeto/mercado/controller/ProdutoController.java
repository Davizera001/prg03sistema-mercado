/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.controller;

// Importa a entidade Produto.
import br.com.ifba.prg03projeto.mercado.entity.Produto;

// Importa o service com as regras e operações de produto.
import br.com.ifba.prg03projeto.mercado.service.ProdutoService;

// Importa List para retornar vários produtos.
import java.util.List;

// Importa ResponseEntity para controlar as respostas HTTP.
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
// Importa RequestParam para receber parâmetros pela URL.
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author Davi
 */

// Indica que esta classe será um controller REST.
@RestController
// Define o caminho base das rotas de produto.
@RequestMapping("/produtos")
public class ProdutoController {
    
    // Service usado para acessar as operações de produto.
    private final ProdutoService produtoService;

    // Construtor usado pelo Spring para injetar o service.
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Rota para cadastrar um novo produto.
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    // Rota para listar todos os produtos ou buscar produtos pelo nome.
@GetMapping
public List<Produto> listarTodos(@RequestParam(required = false) String nome) {

    // Se o parâmetro nome for informado, busca produtos pelo nome.
    if (nome != null && !nome.trim().isEmpty()) {
        return produtoService.buscarPorNome(nome);
    }

    // Se nenhum nome for informado, lista todos os produtos.
    return produtoService.listarTodos();
}

    // Rota para buscar um produto pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Rota para atualizar um produto existente.
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                             @RequestBody Produto produto) {

        return produtoService.buscarPorId(id)
                .map(produtoExistente -> {
                    produto.setId(id);
                    Produto produtoAtualizado = produtoService.salvar(produto);
                    return ResponseEntity.ok(produtoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Rota para deletar um produto pelo ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        if (produtoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
