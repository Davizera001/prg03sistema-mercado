/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.prg03projeto.mercado.filial.controller;

// Importa a entidade Filial.
import br.com.ifba.prg03projeto.mercado.filial.entity.Filial;

// Importa o serviço responsável pelas regras de Filial.
import br.com.ifba.prg03projeto.mercado.filial.service.FilialService;

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
@RequestMapping("/filiais")
public class FilialController {

    // Serviço usado para acessar as operações de Filial.
    private final FilialService filialService;

    // Construtor utilizado pelo Spring para injetar o serviço.
    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    // Cadastra uma nova filial.
    @PostMapping
    public Filial salvar(@RequestBody Filial filial) {
        return filialService.salvar(filial);
    }

    // Lista todas as filiais cadastradas.
    @GetMapping
    public List<Filial> listarTodos() {
        return filialService.listarTodos();
    }

    // Busca uma filial pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<Filial> buscarPorId(@PathVariable Long id) {

        return filialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualiza uma filial existente.
    @PutMapping("/{id}")
    public ResponseEntity<Filial> atualizar(
            @PathVariable Long id,
            @RequestBody Filial filialAtualizada) {

        return filialService.buscarPorId(id)
                .map(filialExistente -> {

                    // Atualiza os dados da filial encontrada.
                    filialExistente.setNome(filialAtualizada.getNome());
                    filialExistente.setEndereco(filialAtualizada.getEndereco());

                    // Salva e retorna a filial atualizada.
                    return ResponseEntity.ok(
                            filialService.salvar(filialExistente)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Remove uma filial pelo ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        // Verifica se a filial existe antes de remover.
        if (filialService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        filialService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
