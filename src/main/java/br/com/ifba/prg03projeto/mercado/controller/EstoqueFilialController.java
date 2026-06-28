/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.controller;

// Importa a entidade EstoqueFilial.
import br.com.ifba.prg03projeto.mercado.entity.EstoqueFilial;

// Importa o serviço responsável pelas regras de EstoqueFilial.
import br.com.ifba.prg03projeto.mercado.service.EstoqueFilialService;

// Importa as coleções utilizadas.
import java.util.List;

// Importa as classes HTTP do Spring.
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

@RestController
@RequestMapping("/estoques-filiais")
public class EstoqueFilialController {

    // Serviço usado para acessar as operações de EstoqueFilial.
    private final EstoqueFilialService estoqueFilialService;

    // Construtor utilizado pelo Spring para injetar o serviço.
    public EstoqueFilialController(
            EstoqueFilialService estoqueFilialService) {

        this.estoqueFilialService = estoqueFilialService;
    }

    // Cadastra um novo registro de estoque.
    @PostMapping
    public EstoqueFilial salvar(
            @RequestBody EstoqueFilial estoqueFilial) {

        return estoqueFilialService.salvar(estoqueFilial);
    }

    // Lista todos os registros de estoque.
    @GetMapping
    public List<EstoqueFilial> listarTodos() {
        return estoqueFilialService.listarTodos();
    }

    // Busca um registro de estoque pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueFilial> buscarPorId(
            @PathVariable Long id) {

        return estoqueFilialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza um registro de estoque existente.
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueFilial> atualizar(
            @PathVariable Long id,
            @RequestBody EstoqueFilial estoqueAtualizado) {

        return estoqueFilialService.buscarPorId(id)
                .map(estoqueExistente -> {

                    // Atualiza os dados do estoque encontrado.
                    estoqueExistente.setQuantidade(
                            estoqueAtualizado.getQuantidade()
                    );

                    estoqueExistente.setFilial(
                            estoqueAtualizado.getFilial()
                    );

                    estoqueExistente.setProduto(
                            estoqueAtualizado.getProduto()
                    );

                    // Salva e retorna o estoque atualizado.
                    return ResponseEntity.ok(
                            estoqueFilialService.salvar(estoqueExistente)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Remove um registro de estoque pelo ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        // Verifica se o registro existe antes de remover.
        if (estoqueFilialService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        estoqueFilialService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}